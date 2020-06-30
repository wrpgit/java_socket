package com.wrpxcx.util;

import com.wrpxcx.swingUi.RqUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: wrp
 * @TODO: 消息列表的双击事件的监听
 * @time: 2020-05-30 20:51
 **/

public class MouseClickedTwiceListener extends MouseAdapter {
    private static  boolean flag = false;		//双击事件已执行时置为真
    private static int clickNum = 1;		//指示鼠标点击次数，默认为单击

    private String userId;
    public MouseClickedTwiceListener(String userId){
        this.userId = userId;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        final MouseEvent me = e;
        MouseClickedTwiceListener.flag= false;
        if (MouseClickedTwiceListener.clickNum == 2) {
            //鼠标点击次数为2调用双击事件
            this.mouseClickedTwice(me);
            //调用完毕clickNum置为1
            MouseClickedTwiceListener.clickNum = 1;
            MouseClickedTwiceListener.flag = true;
            return;
        }
        //新建定时器，双击检测间隔为500ms
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            //指示定时器执行次数
            int num = 0;
            @Override
            public void run() {
                // 双击事件已经执行，取消定时器任务
                if(MouseClickedTwiceListener.flag) {
                    num = 0;
                    MouseClickedTwiceListener.clickNum = 1;
                    this.cancel();
                    return;
                }
                //定时器再次执行，调用单击事件，然后取消定时器任务
                if (num == 1) {
                    //mouseClickedOnce(me);
                    MouseClickedTwiceListener.flag = true;
                    MouseClickedTwiceListener.clickNum = 1;
                    num = 0;
                    this.cancel();
                    return;
                }
                clickNum++;
                num++;
            }
        },new Date(), 500);
    }
    //        protected void mouseClickedOnce(MouseEvent me) {
//            // 单击事件
//            System.out.println("1");
//        }
    private void mouseClickedTwice(MouseEvent me) {
        // 双击事件
        //System.out.println("打开对话框");
        System.out.println(RqUI.getDialogBox());
        RqUI.openDialog(userId);
    }
}
