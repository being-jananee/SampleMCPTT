package com.bluebirdcorp.mcptt.constants;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.entity.SetupNotificationsTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MCPTTConstants {

    public static final String RADIO_START_BEEP = "Radio Start Beep";
    public static final int INVALID_FILE_NAME = -1;
    public static final String CONTENT_STORAGE_BOX = "Content Storage Box";
    public static final String TERMINAL_INFORMATION = "View Terminal Information";
    public static final String NOTIFICATION_SETTINGS = "Notification Settings";
    public static final String SUB_CHANNEL_RADIO_NOTIFICATION = "Sub channel radio notification";
    public static final String VIEW_MORE = "View More";
    public static List<String> STATIC_BEEP_LIST = Arrays.asList("Beep 1","Beep 2","Beep 3","Beep 4","Beep 5","Silent");

    public static  List<SetupNotificationsTemplate>  createNotificationMenus() {
        List<SetupNotificationsTemplate> notificationsTemplates = new LinkedList<>();
        notificationsTemplates.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Radio Start Beep","Beep 1"));
        notificationsTemplates.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.SWITCH_TYPE, R.id.notificationToggle,"Radio Shutdown notification",null));
        notificationsTemplates.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Sub-channel radio notification","Sound"));
        notificationsTemplates.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.SWITCH_TYPE,R.id.notificationToggle,"Message Notification",null));
        notificationsTemplates.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.SWITCH_TYPE,R.id.notificationToggle,"Screen turns on when receiving radio",null));
        return notificationsTemplates;
    }
}
