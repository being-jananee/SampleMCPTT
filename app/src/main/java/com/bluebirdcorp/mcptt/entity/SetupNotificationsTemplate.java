package com.bluebirdcorp.mcptt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SetupNotificationsTemplate {

    public final static int TEXT_TYPE=0;
    public final static int SWITCH_TYPE=1;

    public int componentType;
    public int componentId;
    public String fieldName;
    public String fieldValue;
}
