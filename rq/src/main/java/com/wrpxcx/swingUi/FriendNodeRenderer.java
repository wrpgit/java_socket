package com.wrpxcx.swingUi;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: wrp
 * @TODO: 重写好友节点树的渲染类
 * @time: 2020-05-12 20:34
 **/
public class FriendNodeRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree jTree, Object value, boolean sel, boolean expanded, boolean leaf,
                                                  int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(jTree, value, sel, expanded, leaf, row, hasFocus);

        FriendNode friendNode = (FriendNode) value;
        if (friendNode.getLevel() == 1) {
            if (expanded) {
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/arrow_down.png"));
                icon.setImage((icon.getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
                friendNode.iconLabel.setIcon(icon);
            } else {
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/arrow_right.png"));
                icon.setImage((icon.getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
                friendNode.iconLabel.setIcon(icon);
            }
            return friendNode.getCateView();
        } else if (friendNode.getLevel() >= 2) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + friendNode.getHeadImage() + ".png"));
            icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            friendNode.iconLabel.setIcon(icon);
            return friendNode.getNodeView();
        }
        return friendNode.getCateView();
    }

}
