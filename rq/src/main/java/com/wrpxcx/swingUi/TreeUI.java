package com.wrpxcx.swingUi;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

/**
 * @author: wrp
 * @TODO: 实现tree节点的左对齐
 * @time: 2020-05-13 14:34
 **/
public class TreeUI extends BasicTreeUI {

    // 去除JTree的垂直线
    @Override
    protected void paintVerticalLine(Graphics g, JComponent c, int x, int top,
                                     int bottom) {
    }

    // 去除JTree的水平线
    @Override
    protected void paintHorizontalLine(Graphics g, JComponent c, int y,
                                       int left, int right) {
    }

    // 实现父节点与子节点左对齐
    @Override
    public void setLeftChildIndent(int newAmount) {

    }

    // 实现父节点与子节点右对齐
    @Override
    public void setRightChildIndent(int newAmount) {

    }

    @Override
    public void setExpandedIcon(Icon newG) {

    }

    @Override
    public void setCollapsedIcon(Icon newG) {

    }


}
