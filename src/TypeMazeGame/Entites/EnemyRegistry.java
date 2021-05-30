package TypeMazeGame.Entites;

public class EnemyRegistry {

    public static Enemy getEnemy(final int enemyId){
        Enemy enemy = null;

        switch (enemyId){
            case 1 ->{
                enemy = new Flea(enemyId, "Flea", 10);
            }
        }

        return enemy;
    }

}
