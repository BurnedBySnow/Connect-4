public class ChipFactory {
    public Chip getChip(ChipType chipType){
        Chip chip;
        switch (chipType){
            case DIAMOND -> chip = new DiamondChip();
            case HEART -> chip = new HeartChip();
            default -> chip = new RoundChip();
        }
        return chip;
    }
}
