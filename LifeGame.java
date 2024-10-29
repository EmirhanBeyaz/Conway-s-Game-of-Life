
public class LifeGame {
    
    final int habitatSatir = 19;
    final int habitatSutun = 19;

    int hucreHabitat[][];
    int hucreHabitatTmp[][];

    int[] pulsarSatir;
    int[] pSatir;

    int pulsarSutun[];

    public LifeGame() {
        // pulsar desni oluşması için gerekli ön tanımlamalar
        pSatir = new int[] { 3, 8, 10, 15 };
        pulsarSatir = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 };
        pulsarSutun = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 };

        // gerçek yaşam alanı ve değişikliklerin yapılacağı yedek yaşam alanı tanımı
        hucreHabitat = new int[habitatSatir][habitatSutun];
        hucreHabitatTmp = new int[habitatSatir][habitatSutun];

        // tüm yedek ve gerçek yaşam alanı sıfırlanıyor
        int c = 0;
        for (int i = 0; i < habitatSatir; i++) {
            for (int y = 0; y < habitatSutun; y++) {
                hucreHabitatTmp[i][y] = c;
                hucreHabitat[i][y] = c;
            }
        }
        // pulsar deseni gerçek yaşam alanına atanıyor
        for (int satir = 0; satir < pSatir.length; satir++) {
            for (int sutun = 0; sutun < habitatSutun; sutun++) {
                hucreHabitat[pSatir[satir]][sutun] = pulsarSatir[sutun];
            }
        }

        for (int sutun = 0; sutun < pSatir.length; sutun++) {
            for (int satir = 0; satir < habitatSutun; satir++) {
                hucreHabitat[satir][pSatir[sutun]] = pulsarSatir[satir];
            }
        }
    }

    public void drawHabitat() {
        // gerçek yaşam alanı (hucreHabitat) ekrana çizdiriliyor
        // *******KODLANACAK*********
        for (int i = 0; i < habitatSatir;i++){
            for (int j = 0; j < habitatSutun;j++){
                if (hucreHabitat[i][j] == 1){
                    System.out.print("#");
                }
                else{
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    public int komsuCanliSayisi(int satir, int sutun) {
        int canliKomsuSayisi = 0;
        // koordinatları girilen hücre merkezde olmak üzere 3x3 lük alanda
        // canlı komşu sayısı tespiti yapılıyor. Eğer kendiside canlı ise
        // canlı komşuya eklenmemelidir.
        // *******KODLANACAK*********
        for (int i = -1;i <= 1;i++){
            for (int j = -1;j <= 1;j++){
                if (i == 0 && j == 0) {
                    continue;  
                }
                int komsuSatir = satir + i;
                int komsuSutun = sutun + j;
                if (komsuSatir >= 0 && komsuSatir < habitatSatir && komsuSutun >= 0 && komsuSutun < habitatSutun){
                    canliKomsuSayisi += hucreHabitat[komsuSatir][komsuSutun];
                }
            }
        }
        return canliKomsuSayisi;
    }

    public void newHabitatRule() {
        // Life Game'in 4 kuralına göre gerçek habitata bakılarak
        // bir sonraki iterasyon için geçici habitat (hucreHabitatTmp)
        // güncelleniyor
        // *******KODLANACAK*********
        for (int i = 0; i < habitatSatir; i++) {
            for (int j = 0; j < habitatSutun; j++) {
                int cks = komsuCanliSayisi(i, j);
                if (hucreHabitat[i][j] == 1){
                    if (cks == 2 || cks == 3){
                        hucreHabitatTmp[i][j] = 1;
                    }
                    else if (cks < 2){
                        hucreHabitatTmp[i][j] = 0;
                    }
                    else{
                        hucreHabitatTmp[i][j] = 0;
                    }
                }
                else{
                    if (cks == 3){
                        hucreHabitatTmp[i][j] = 1;
                    }
                    else{
                        hucreHabitatTmp[i][j] = 0;
                    }
                }   
            }   
       }
    }
    //A live cell dies if it has fewer than two live neighbors.
    //A live cell with two or three live neighbors lives on to the next generation.
    //A live cell with more than three live neighbors dies.
    //A dead cell will be brought back to live if it has exactly three live neighbors.
        
    public void copyHabitat() {
        // yedek hücreden tekrar orjinaline yükleme yap
        // *******KODLANACAK*********
        for (int i = 0;i < habitatSatir;i++){
            for (int j = 0;j < habitatSutun;j++){
                hucreHabitat[i][j] = hucreHabitatTmp[i][j];
            }
        }    
    }

    public static void main(String[] args) throws Exception {
        LifeGame lg = new LifeGame();
        for (int i = 0; i < 20; i++) {
            lg.drawHabitat();
            lg.newHabitatRule();
            lg.copyHabitat();
            System.out.println();
            Thread.sleep(1500);
        }   
    }
}
