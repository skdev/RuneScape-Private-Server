/**
 * scape-emulator-final
 * Copyright (c) 2014 Justin Conner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in  the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/license/>.
 */
package net.scapeemulator.cache.def;

import net.scapeemulator.cache.util.ByteBufferUtils;

import java.nio.ByteBuffer;

public final class ObjectDefinition {

    private String name;
    private String description;

    private String[] options;
    
    private boolean members;

    private int width;
    private int length;
    private int anInt768;

    private boolean solid;
    private boolean impenetrable;
    private boolean walkable;

    @SuppressWarnings("unused")
    public static ObjectDefinition decode(ByteBuffer buffer) {
        ObjectDefinition def = new ObjectDefinition();
        def.name = "null";
        def.width = 1;
        def.length = 1;
        def.options = new String[5];
        def.impenetrable = true;
        def.solid = true;
        while (true) {
            int opcode = buffer.get() & 0xFF;
            if (opcode == 0)
                break;
            if(opcode == 1) {
                int i = buffer.get() & 0xff;
                if(i > 0) {
                    for(int var5 = 0; var5 < i; var5++) {
                        buffer.getShort();
                        buffer.get();
                    }
                }
            } else if (opcode == 2) {
                def.name = ByteBufferUtils.getJagexString(buffer);
            } else if (opcode == 5) {
                int i = buffer.get() & 0xff;
                if(i > 0) {
                    for(int var5 = 0; var5 < i; var5++) {
                        buffer.getShort();
                    }
                }
            } else if (opcode == 14) {
                def.width = buffer.get() & 0xff;
            } else if (opcode == 15) {
                def.length = buffer.get() & 0xff;
            } else if(opcode == 17) {
                def.impenetrable = false;
                def.solid = false;
            } else if (opcode == 18) {
                //def.solid = false;
            } else if (opcode == 19) {
                int i = buffer.get();
            } else if (opcode == 24) {
                int i = buffer.getShort();
            } else if (opcode == 27) {
                //TODO: def.impenetrable = true; ?
            } else if (opcode == 28) {
                int i = buffer.get();
            } else if (opcode == 29) {
                int i = buffer.get();
            } else if (opcode >= 30 && opcode < 35)
                def.options[opcode-30] = ByteBufferUtils.getJagexString(buffer);
            else if (opcode == 39) {
                int i = buffer.get();
            } else if (opcode == 40) {
                int length = buffer.get() & 0xFF;
                for(int index = 0; index < length; index++) {
                    buffer.getShort();
                    buffer.getShort();
                }
            } else if (opcode == 41) {
                int length = buffer.get() & 0xFF;
                for(int index = 0; index < length; index++) {
                    int i = buffer.getShort() & 0xFFFFF;
                    int i2 = buffer.getShort() & 0xFFFFF;
                }
            } else if (opcode == 42) {
                int length = buffer.get() & 0xFF;
                for(int index = 0; index < length; index++) {
                    int i = buffer.get();
                }
            } else if(opcode == 60) {
                int i = buffer.getShort() & 0xffff;
            } else if(opcode == 65) {
                int i = buffer.getShort() & 0xffff;
            } else if(opcode == 66) {
                int i = buffer.getShort() & 0xffff;
            } else if(opcode == 67) {
                int i = buffer.getShort() & 0xffff;
            } else if(opcode == 69) {
                def.anInt768 = buffer.get() & 0xff;
            } else if(opcode == 70) {
                int i = buffer.getShort();
            } else if(opcode == 71) {
                int i = buffer.getShort();
            } else if(opcode == 72) {
                int i = buffer.getShort();
            } else if(opcode == 74) {
                def.walkable = true;
            } else if(opcode == 75) {
                int i = buffer.get();
            } else if(opcode == 77 || opcode == 92) {
                int i = buffer.getShort() & 0xffff;
                int i2 = buffer.getShort() & 0xffff;

                if(opcode == 92) {
                    int i3 = buffer.getShort();
                }

                int i4 = buffer.get() & 0xff;

                for(int var6 = 0; var6 <= i4; var6++) {
                    int i5 = buffer.getShort();
                }
            } else if (opcode == 78) {
                buffer.getShort();
                buffer.get();
            } else if (opcode == 79) {
                int i = buffer.getShort() & 0xffff;
                int i2 = buffer.getShort() & 0xffff;
                int i3 = buffer.get() & 0xff;
                int i4 = buffer.get() & 0xff;
                for(int counter = 0; counter < i4; ++counter) {
                    int i5 = buffer.getShort() & 0xffff;
                }
            } else if(opcode == 81) {
                int i = buffer.get() & 0xff;
            } else if (opcode == 93){
                int i = buffer.getShort() & 0xFFFFF;
            } else if (opcode == 99) {
                int i = buffer.get() & 0xff;
                int i2 = buffer.getShort() & 0xffff;
            } else if (opcode == 100) {
                int i = buffer.get() & 0xff;
                int i2 = buffer.getShort() & 0xffff;
            } else if (opcode == 101) {
                int i = buffer.get() & 0xff;
            } else if (opcode == 102){
                int i = buffer.getShort() & 0xFFFFF;
            } else if (opcode == 104) {
                buffer.get();
            } else if (opcode == 106) {
                int i2 = buffer.get() & 0xFF;
                for(int i = 0; i < i2; i++) {
                    buffer.getShort();
                    buffer.get();
                }
            } else if (opcode == 107) {
                buffer.getShort();
            } else if (opcode >= 150 && opcode < 155) {
                def.options[opcode - 150] = ByteBufferUtils.getJagexString(buffer);
            } else if (opcode == 249) {
                int length = buffer.get() & 0xFF;
                for (int index = 0; index < length; index++) {
                    boolean stringInstance = buffer.get() == 1;
                    int key = ByteBufferUtils.getTriByte(buffer);
                    Object value = stringInstance ? ByteBufferUtils.getJagexString(buffer) : buffer.getInt();
                }
            }
        }

        if(def.walkable) {
            def.solid = false;
            def.impenetrable = false;
        }
        return def;
    }

    public String getName() {
        return name;
    }

    public boolean hasOptions() {
        for(int i = 0; i < options.length; i++) {
            if(options[i] != null) {
                return true;
            }
        }
        return false;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isImpenetrable() {
        return impenetrable;
    }

    public boolean isSolid() {
        return solid;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getAnInt768() {
        return anInt768;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMembers() {
		return members;
	}

	public void setMembers(boolean members) {
		this.members = members;
	}
}
