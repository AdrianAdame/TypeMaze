package TypeMazeGame.Entites;

public class ItemRegistry {

    public static Item getItem(final int itemId){
        Item item = null;

        switch (itemId){
            case 1 ->{
                item = new CoinsBag(itemId, "Coins bag", 100);
            }
            case 2 ->{
                item = new CoinsChest(itemId, "Coins chest", 1000);
            }
        }

        return item;
    }

}
