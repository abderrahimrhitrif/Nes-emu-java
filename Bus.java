package nes;

public class Bus {
    public olc6502 cpu;
    public byte ram[];
    
    public Bus() {
        cpu = new olc6502();
        cpu.connectBus(this);
        ram = new byte[64*1024];
    }

    public void write(int addr, byte data) {
        if (addr >= 0x0000 && addr <= 0xFFFF){
            ram[addr] = data;
        }
    }

    public byte read(int addr, boolean bReadOnly) {
        if (addr >= 0x0000 && addr <= 0xFFFF){
            return ram[addr];
        }
        return 0x00; 
    }
}
