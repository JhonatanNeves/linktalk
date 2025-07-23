package com.example.linktalk.ui.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp

fun Modifier.bottomBorder(color: Color, strokeWidth: Dp) = this.drawBehind {
    val strokeWidthPx = strokeWidth.toPx()

    val with = size.width
    val height = size.height - strokeWidthPx / 2

    drawLine(
        color = color,
        start = Offset(x = 0f, y = height),
        end = Offset(x = with, y = height),
        strokeWidth = strokeWidthPx
    )
}

private class BottonBorderNode(
    var color: Color,
    var strokeWidth: Dp,
) : DrawModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        val strokeWidthPx = strokeWidth.toPx()

        val with = size.width
        val height = size.height - strokeWidthPx / 2

        drawLine(
            color = color,
            start = Offset(x = 0f, y = height),
            end = Offset(x = with, y = height),
            strokeWidth = strokeWidthPx
        )
    }
}

private data class BottomBorderElement(
    val color: Color,
    val strokeWidth: Dp,
) : ModifierNodeElement<BottonBorderNode>() {
    override fun create(): BottonBorderNode {
        return BottonBorderNode(color, strokeWidth)
    }

    override fun update(node: BottonBorderNode) {
        node.color = color
        node.strokeWidth = strokeWidth
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "bottomBorder"
        properties["color"] = color
        properties["strokeWidth"] = strokeWidth
    }
}

fun Modifier.bottomBorder2(color: Color, strokeWidth: Dp) =
    this then BottomBorderElement(color, strokeWidth)
