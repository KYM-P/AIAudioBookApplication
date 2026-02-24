package com.design.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.design.ripple.circleRipple
import com.example.design.R

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = TextStyle.Default.copy(fontSize = 18.sp),
    leftIcon: (@Composable () -> Unit)? = null,
    rightIcon: (@Composable () -> Unit)? = null
) {
    ConstraintLayout(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        val (leftIconRef, titleRef, rightIconRef) = createRefs()

        /* leftIcon */
        Box(
            modifier = Modifier
                .constrainAs(leftIconRef) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
            contentAlignment = Alignment.Center
        ) {
            leftIcon?.let { it() }
        }

        /* title */
        Box(
            modifier = Modifier
                .constrainAs(titleRef) {
                    centerTo(parent)
                },
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                text = title,
                style = titleStyle
            )
        }

        /* rightIcon */
        Box(
            modifier = Modifier
                .constrainAs(rightIconRef) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(parent)
                },
            contentAlignment = Alignment.Center
        ) {
            rightIcon?.let { it() }
        }
    }
}

object TopBarIcon {
    fun menuIcon(
        modifier: Modifier = Modifier,
        clickable: Boolean = true,
        onClick: () -> Unit = {}
    ) = @Composable {
        RippledIcon(
            modifier = modifier,
            imageVector = ImageVector.vectorResource(R.drawable.ic_menu),
            clickable = clickable,
            onClick = onClick
        )
    }
    fun notificationIcon(
        modifier: Modifier = Modifier,
        clickable: Boolean = true,
        onClick: () -> Unit = {}
    ) = @Composable {
        RippledIcon(
            modifier = modifier,
            imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
            clickable = clickable,
            onClick = onClick
        )
    }
    @Composable
    private fun RippledIcon(
        imageVector: ImageVector,
        clickable: Boolean,
        modifier: Modifier = Modifier,
        onClick: () -> Unit = {}
    ) {
        Icon(
            modifier = modifier.then(
                if(clickable) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = circleRipple(),
                        onClick = onClick
                    )
                }
                else {
                    Modifier
                }
            ),
            imageVector = imageVector,
            contentDescription = "TopBarIcon"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    TopBar(
        title = "오디오북",
        leftIcon = TopBarIcon.menuIcon(),
        rightIcon = TopBarIcon.notificationIcon()
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarRightNullPreview() {
    TopBar(
        title = "오디오북",
        leftIcon = TopBarIcon.menuIcon()
    )
}