package api.packet;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastItemShopPacket implements Packet {

    private final BroadcastType type;
    private final String content;
    private final Integer amount;

    public BroadcastItemShopPacket(BroadcastType type, String content, Integer amount) {
        this.type = type;
        this.content = content;
        this.amount = amount;
    }

    public BroadcastType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
    
    public Integer getAmount() {
        return amount;
    }

}
