package de.dytanic.cloudnet.driver.channel;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.DriverEnvironment;
import de.dytanic.cloudnet.driver.serialization.ProtocolBuffer;
import de.dytanic.cloudnet.driver.serialization.SerializableObject;
import org.jetbrains.annotations.NotNull;

public class ChannelMessageSender implements SerializableObject {

    private String name;
    private DriverEnvironment type;

    public ChannelMessageSender(@NotNull String name, @NotNull DriverEnvironment type) {
        this.name = name;
        this.type = type;
    }

    public static ChannelMessageSender self() {
        return new ChannelMessageSender(CloudNetDriver.getInstance().getComponentName(), CloudNetDriver.getInstance().getDriverEnvironment());
    }

    public String getName() {
        return this.name;
    }

    public DriverEnvironment getType() {
        return this.type;
    }

    @Override
    public void write(ProtocolBuffer buffer) {
        buffer.writeString(this.name);
        buffer.writeEnumConstant(this.type);
    }

    @Override
    public void read(ProtocolBuffer buffer) {
        this.name = buffer.readString();
        this.type = buffer.readEnumConstant(DriverEnvironment.class);
    }
}
