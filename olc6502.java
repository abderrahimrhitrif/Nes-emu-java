package nes;

import nes.Bus;
import java.util.ArrayList;

public class Instruction{
    public String name;
    public Function operate;
    public Function addrmode;
    public byte cycles;

    public Instruction(String name, Function operate, Function addrmode, byte cycles){
        this.name = name;
        this.operate = operate;
        this.addrmode = addrmode;
        this.cycles = cycles;
    }
}
public class olc6502 {
    public Bus bus;
    ArrayList<Instruction> lookup  = new ArrayList<>();

    // Registers
    public byte a = 0x00;
    public byte x = 0x00;
    public byte y = 0x00;
    public byte stkp = 0x00;
    public int pc = 0x0000;
    public byte status = 0x00;

    public byte fetched = 0x00;
    public int addr_bus = 0x0000;
    public int addr_rel = 0x00;
    public byte opcode = 0x00;
    public byte cycles = 0;

    // Address Modes
    public byte IMP(){ return 0x00;};
    public byte IMM(){ return 0x00;};
    public byte ZP0(){ return 0x00;};
    public byte ZPX(){ return 0x00;};
    public byte ZPY(){ return 0x00;};
    public byte REL(){ return 0x00;};
    public byte ABS(){ return 0x00;};
    public byte ABX(){ return 0x00;};
    public byte ABY(){ return 0x00;};
    public byte IND(){ return 0x00;};
    public byte IZX(){ return 0x00;};
    public byte IZY(){ return 0x00;};
    // Opcodes
    public byte ADC(){ return 0x00;};
    public byte AND(){ return 0x00;};
    public byte ASL(){ return 0x00;};
    public byte BCC(){ return 0x00;};
    public byte BCS(){ return 0x00;};
    public byte BEQ(){ return 0x00;};
    public byte BIT(){ return 0x00;};
    public byte BMI(){ return 0x00;};
    public byte BNE(){ return 0x00;};
    public byte BPL(){ return 0x00;};
    public byte BRK(){ return 0x00;};
    public byte BVC(){ return 0x00;};
    public byte BVS(){ return 0x00;};
    public byte CLC(){ return 0x00;};
    public byte CLD(){ return 0x00;};
    public byte CLI(){ return 0x00;};
    public byte CLV(){ return 0x00;};
    public byte CMP(){ return 0x00;};
    public byte CPX(){ return 0x00;};
    public byte CPY(){ return 0x00;};
    public byte DEC(){ return 0x00;};
    public byte DEX(){ return 0x00;};
    public byte DEY(){ return 0x00;};
    public byte EOR(){ return 0x00;};
    public byte INC(){ return 0x00;};
    public byte INX(){ return 0x00;};
    public byte INY(){ return 0x00;};
    public byte JMP(){ return 0x00;};
    public byte JSR(){ return 0x00;};
    public byte LDA(){ return 0x00;};
    public byte LDX(){ return 0x00;};
    public byte LDY(){ return 0x00;};
    public byte LSR(){ return 0x00;};
    public byte NOP(){ return 0x00;};
    public byte ORA(){ return 0x00;};
    public byte PHA(){ return 0x00;};
    public byte PHP(){ return 0x00;};
    public byte PLA(){ return 0x00;};
    public byte PLP(){ return 0x00;};
    public byte ROL(){ return 0x00;};
    public byte ROR(){ return 0x00;};
    public byte RTI(){ return 0x00;};
    public byte RTS(){ return 0x00;};
    public byte SBC(){ return 0x00;};
    public byte SEC(){ return 0x00;};
    public byte SED(){ return 0x00;};
    public byte SEI(){ return 0x00;};
    public byte STA(){ return 0x00;};
    public byte STX(){ return 0x00;};
    public byte STY(){ return 0x00;};
    public byte TAX(){ return 0x00;};
    public byte TAY(){ return 0x00;};
    public byte TSX(){ return 0x00;};
    public byte TXA(){ return 0x00;};
    public byte TXS(){ return 0x00;};
    public byte TYA(){ return 0x00;};
    public byte XXX(){ return 0x00;};

    private void initializeInstructions() {
        lookup.add(new Instruction("BRK", (cpu) -> cpu.BRK(), (cpu) -> cpu.IMM(), (byte) 7));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("ASL", (cpu) -> cpu.ASL(), (cpu) -> cpu.ZP0(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("PHP", (cpu) -> cpu.PHP(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("ASL", (cpu) -> cpu.ASL(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("ASL", (cpu) -> cpu.ASL(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("BPL", (cpu) -> cpu.BPL(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("ASL", (cpu) -> cpu.ASL(), (cpu) -> cpu.ZPX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("CLC", (cpu) -> cpu.CLC(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("ORA", (cpu) -> cpu.ORA(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("ASL", (cpu) -> cpu.ASL(), (cpu) -> cpu.ABX(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("JSR", (cpu) -> cpu.JSR(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("BIT", (cpu) -> cpu.BIT(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("ROL", (cpu) -> cpu.ROL(), (cpu) -> cpu.ZP0(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("PLP", (cpu) -> cpu.PLP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("ROL", (cpu) -> cpu.ROL(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("BIT", (cpu) -> cpu.BIT(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("ROL", (cpu) -> cpu.ROL(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("BMI", (cpu) -> cpu.BMI(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("ROL", (cpu) -> cpu.ROL(), (cpu) -> cpu.ZPX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("SEC", (cpu) -> cpu.SEC(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("AND", (cpu) -> cpu.AND(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("ROL", (cpu) -> cpu.ROL(), (cpu) -> cpu.ABX(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("RTI", (cpu) -> cpu.RTI(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("LSR", (cpu) -> cpu.LSR(), (cpu) -> cpu.ZP0(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("PHA", (cpu) -> cpu.PHA(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("LSR", (cpu) -> cpu.LSR(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("JMP", (cpu) -> cpu.JMP(), (cpu) -> cpu.ABS(), (byte) 3));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("LSR", (cpu) -> cpu.LSR(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("BVC", (cpu) -> cpu.BVC(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("LSR", (cpu) -> cpu.LSR(), (cpu) -> cpu.ZPX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("CLI", (cpu) -> cpu.CLI(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("EOR", (cpu) -> cpu.EOR(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("LSR", (cpu) -> cpu.LSR(), (cpu) -> cpu.ABX(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("RTS", (cpu) -> cpu.RTS(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("ROR", (cpu) -> cpu.ROR(), (cpu) -> cpu.ZP0(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("PLA", (cpu) -> cpu.PLA(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("ROR", (cpu) -> cpu.ROR(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("JMP", (cpu) -> cpu.JMP(), (cpu) -> cpu.IND(), (byte) 5));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("ROR", (cpu) -> cpu.ROR(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("BVS", (cpu) -> cpu.BVS(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("ROR", (cpu) -> cpu.ROR(), (cpu) -> cpu.ZPX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("SEI", (cpu) -> cpu.SEI(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("ADC", (cpu) -> cpu.ADC(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("ROR", (cpu) -> cpu.ROR(), (cpu) -> cpu.ABX(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("STY", (cpu) -> cpu.STY(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("STX", (cpu) -> cpu.STX(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("DEY", (cpu) -> cpu.DEY(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("TXA", (cpu) -> cpu.TXA(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("STY", (cpu) -> cpu.STY(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("STX", (cpu) -> cpu.STX(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("BCC", (cpu) -> cpu.BCC(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.IZY(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("STY", (cpu) -> cpu.STY(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("STX", (cpu) -> cpu.STX(), (cpu) -> cpu.ZPY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("TYA", (cpu) -> cpu.TYA(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.ABY(), (byte) 5));
        lookup.add(new Instruction("TXS", (cpu) -> cpu.TXS(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("STA", (cpu) -> cpu.STA(), (cpu) -> cpu.ABX(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("LDY", (cpu) -> cpu.LDY(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("LDX", (cpu) -> cpu.LDX(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("LDY", (cpu) -> cpu.LDY(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("LDX", (cpu) -> cpu.LDX(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 3));
        lookup.add(new Instruction("TAY", (cpu) -> cpu.TAY(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("TAX", (cpu) -> cpu.TAX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("LDY", (cpu) -> cpu.LDY(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("LDX", (cpu) -> cpu.LDX(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("BCS", (cpu) -> cpu.BCS(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("LDY", (cpu) -> cpu.LDY(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("LDX", (cpu) -> cpu.LDX(), (cpu) -> cpu.ZPY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("CLV", (cpu) -> cpu.CLV(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("TSX", (cpu) -> cpu.TSX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("LDY", (cpu) -> cpu.LDY(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("LDA", (cpu) -> cpu.LDA(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("LDX", (cpu) -> cpu.LDX(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("CPY", (cpu) -> cpu.CPY(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("CPY", (cpu) -> cpu.CPY(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("DEC", (cpu) -> cpu.DEC(), (cpu) -> cpu.ZP0(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("INY", (cpu) -> cpu.INY(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("DEX", (cpu) -> cpu.DEX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("CPY", (cpu) -> cpu.CPY(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("DEC", (cpu) -> cpu.DEC(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("BNE", (cpu) -> cpu.BNE(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("DEC", (cpu) -> cpu.DEC(), (cpu) -> cpu.ZPX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("CLD", (cpu) -> cpu.CLD(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("NOP", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("CMP", (cpu) -> cpu.CMP(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("DEC", (cpu) -> cpu.DEC(), (cpu) -> cpu.ABX(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("CPX", (cpu) -> cpu.CPX(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.IZX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("CPX", (cpu) -> cpu.CPX(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.ZP0(), (byte) 3));
        lookup.add(new Instruction("INC", (cpu) -> cpu.INC(), (cpu) -> cpu.ZP0(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 5));
        lookup.add(new Instruction("INX", (cpu) -> cpu.INX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.IMM(), (byte) 2));
        lookup.add(new Instruction("NOP", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.SBC(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("CPX", (cpu) -> cpu.CPX(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.ABS(), (byte) 4));
        lookup.add(new Instruction("INC", (cpu) -> cpu.INC(), (cpu) -> cpu.ABS(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("BEQ", (cpu) -> cpu.BEQ(), (cpu) -> cpu.REL(), (byte) 2));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.IZY(), (byte) 5));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 8));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.ZPX(), (byte) 4));
        lookup.add(new Instruction("INC", (cpu) -> cpu.INC(), (cpu) -> cpu.ZPX(), (byte) 6));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 6));
        lookup.add(new Instruction("SED", (cpu) -> cpu.SED(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.ABY(), (byte) 4));
        lookup.add(new Instruction("NOP", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 2));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.NOP(), (cpu) -> cpu.IMP(), (byte) 4));
        lookup.add(new Instruction("SBC", (cpu) -> cpu.SBC(), (cpu) -> cpu.ABX(), (byte) 4));
        lookup.add(new Instruction("INC", (cpu) -> cpu.INC(), (cpu) -> cpu.ABX(), (byte) 7));
        lookup.add(new Instruction("???", (cpu) -> cpu.XXX(), (cpu) -> cpu.IMP(), (byte) 7));

    }

    public enum FLAGS6502{
        C(1 << 0), // Cary bit
        Z(1 << 1), // Zero
        I(1 << 2), // Disable interrupt
        D(1 << 3), // Decimal Mode
        B(1 << 4), // Break
        U(1 << 5), // Unused
        V(1 << 6), // Overfloyy
        N(1 << 7); // Negative
}

    public olc6502(){
        initializeInstructions();
    }

    public void connectBus(Bus n) {
        this.bus = n;
    }

    public byte read(int addr) {
        return bus.read(addr);
    }

    public void write(int addr, byte data) {
        bus.write(addr, data);
    }
    public void clock(){
        if (cycles == 0){
        opcode = read(pc);
        pc++;
        Instruction instruction = lookup.get(opcode & 0xFF);
        cycles = instruction.cycles;

        if ((instruction.addrmode != null) && (instruction.operate != null)){
            byte additinal_cycle1 = (byte) instruction.addrmode.apply(this);
            byte additinal_cycle2 = (byte) instruction.operate.apply(this);
            cycles += additinal_cycle1 + additinal_cycle2;
        }
        cycles--;
    }
    }
}
