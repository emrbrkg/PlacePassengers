package DataStructuresProje;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class placePassengers {
    static int size = 40;
    static double[][] DM = new double[size][size];
    static String[] names = {"Andrew Rosario", "Austin Ortiz", "Lauren Alexander", "Mark Morgan", "Hunter Jones", "Sharon Thomas", "Hannah Boyd",
            "Lindsey Eaton", "Kevin Choir", "Leslie Rhodes", "Vanessa Myers", "Abigail Dillon", "Autumn Hill", "Jeffrey Smith", "Holly Tran", "Jennifer Williams",
            "Matthew Hamilton", "Cassidy West", "Debra Chavez", "Bruce Oneal", "Heather Owen", "Steven Smith", "Nicholas Wilcox", "Barbara Brooks", "Richard Brown",
            "Michael Johnson", "Tommy Moses", "Jeremy Eaton", "Joshua Reynolds", "Mary Hudson", "Christina Flores", "Scott Curry", "Laura Parker", "Alicia Williams",
            "Stacey Holden", "Nathan Morse", "Robert Kelly", "Janet Monroe", "Bethany Anderson", "Christopher Meyer"};
    static int[][] busMatrice = new int[size / 4][4];

    public static void main(String[] args) {
        DM = createDm(size);
        int[][] otoMatrice = createBusMatrice(busMatrice);
        List<Integer> addedPassengers = new ArrayList<>();  //Koltuklara eklenen yolcuların ekleneceği arraylist  // ArrayList to add placed passengers
        List<Double> distancesSum = new ArrayList<>();
        placePassengers(DM, otoMatrice, addedPassengers, distancesSum);
        //Düzgün şekilde çıktı alabilmek için yapılan düzenlemeler
        System.out.println();
        System.out.println("  Bus Passengers Numbers ");
        System.out.println("  -----------------------");
        intPrintMatrice(otoMatrice);   //koltuklara oturan yolcu numaralarının yazdırılması.  // Printing passengers placed to seats
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        printPassengerInfos(otoMatrice, names);
        System.out.println();

    }

    // Method to create distance matrice
    // Uzaklık matrisini oluşturan metot
    public static double[][] createDm(int size) {
        double[][] DistanceMatrice = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                Random r = new Random();
                if (i == j) {
                    DistanceMatrice[i][j] = 0;
                } else{
                    DistanceMatrice[i][j] = DistanceMatrice[j][i] = r.nextDouble(0.1, 9.9);
                }
            }
        }
        return DistanceMatrice;
    }


    // Method that prints passenger nos on the bus matrice
    // Yolcu numaralarını otobüs matrisi üzerinde yazdıran metot
    public static void intPrintMatrice(int[][] Matrice) {
        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println();
            for (int j = 0; j < 4; j++) {
                System.out.printf("    %.2s", Matrice[i][j]);
            }
        }
    }

    // Method that create bus matrice
    // Otobüs matrisini oluşturan metot
    public static int[][] createBusMatrice(int[][] busMatrice) {
        int k = 1;
        for (int i = 0; i < size / 4; i++) {
            for (int j = 0; j < 4; j++) {
                busMatrice[i][j] = k;
                k++;
            }
        }
        return busMatrice;
    }

    //Yolcuları kurala uygun şekilde yerleştiren metot
    public static void placePassengers(double[][] DistanceMatrice, int[][] busMatrice, List<Integer> addedPassengers, List<Double> distancesSum) {
        Random r = new Random();
        int firstPassengerNo = r.nextInt(0, size - 1);  //İlk yolcuyu random olarak oluşturma  // Creating first passenger randomly.
        busMatrice[0][0] = firstPassengerNo;  //ilk yolcunun ilk koltuğa atanma işlemi  // initializeing first passenger to first seat.
        int nextPassenger = firstPassengerNo;  //for döngüsü içinde ilk yolcuyu kullanarak diğer yolcuların oturtulması işlemine yardımcı olacak değişken  // By using first passenger to help to place other passengers.
        addedPassengers.add(nextPassenger);  //Oturan yolcuların tekrardan hesaba katılmaması için oluşturulan arrayliste ilk yolcuyu ekleme  // Adding first passenger to the arrayList in order to avoid reconsidiring already placed passengers.

        int k = 1;
        int wantedValue = 0;  //Yerleştirilecek yolcunun bulunması için yapılan değer tanımlaması   // Value initializing to find passenger to place
        for (int i = 0; i < 3; i++) {  //2,3,4 nolu koltuklara oturacak yolcuları belirleme
            double distance = 40;  //En küçük uzaklığı bulabilmek için kullanılan değişken
            for (int d = 0; d < size; d++) {  //Minimum uzaklığı bulmak için tüm yolcuların kontrolü
                if (d != nextPassenger) {  //Yolcunun kendisiyle olan uzaklığını ihmal etmek için.
                    if (DistanceMatrice[nextPassenger][d] < distance) {
                        distance = DistanceMatrice[nextPassenger][d];
                        wantedValue = d;
                    }
                }
            }
            double roundOff = Math.round(distance * 100.0) / 100.0;  //Uzaklığın formatlı şekilde yazdırılması  // Printing distances as formatted
            distancesSum.add(roundOff);
            nextPassenger = wantedValue;
            busMatrice[0][k] = nextPassenger;

            for (Integer item : addedPassengers) {  //Eklenen yolcuların tekrardan hesaba katılmaması için uzaklık değerlerinin büyütülmesi  // Enlarging distance values so that added passengers are not taken into account again
                DistanceMatrice[nextPassenger][item] = 40;
                DistanceMatrice[item][nextPassenger] = 40;
            }
            addedPassengers.add(nextPassenger);
            k++;
        }

        for (int t = 1; t < 10; t++) {     //ilk dört yolcu harici kalanlari yerlestirme  // Placing remaining passengers except first 4 passengers.
            for (int j = 0; j < busMatrice[t].length; j++) {
                double sum = 40;
                if (busMatrice[t][j] % 4 == 1) {  //sol cam kenarındaki yolcuları yerleştirme  // Placing left window side passengers
                    for (int i = 0; i < size; i++) {
                        double total = 0;
                        total += DistanceMatrice[busMatrice[t - 1][j]][i];
                        total += DistanceMatrice[busMatrice[t - 1][j + 1]][i];

                        if (total < sum) {
                            sum = total;
                            nextPassenger = i;
                            busMatrice[t][j] = i;

                        }

                    }
                    for (Integer item : addedPassengers) {
                        DistanceMatrice[nextPassenger][item] = 40;
                        DistanceMatrice[item][nextPassenger] = 40;
                    }
                    double roundOff = Math.round(sum * 100.0) / 100.0;
                    distancesSum.add(roundOff);
                    addedPassengers.add(nextPassenger);
                    k++;

                } else if (busMatrice[t][j] % 4 == 2 || busMatrice[t][j] % 4 == 3) {  //Koridor kenarındaki yolcuları yerleştirme // Placing aiscle side passengers

                    for (int i = 0; i < size; i++) {
                        double total = 0;
                        total += DistanceMatrice[busMatrice[t - 1][j]][i];
                        total += DistanceMatrice[busMatrice[t - 1][j - 1]][i];
                        total += DistanceMatrice[busMatrice[t - 1][j + 1]][i];
                        total += DistanceMatrice[busMatrice[t][j - 1]][i];

                        if (total < sum) {
                            sum = total;
                            nextPassenger = i;
                            busMatrice[t][j] = i;
                        }

                    }
                    for (Integer item : addedPassengers) {
                        DistanceMatrice[nextPassenger][item] = 40;
                        DistanceMatrice[item][nextPassenger] = 40;
                    }
                    double roundOff = Math.round(sum * 100.0) / 100.0;
                    distancesSum.add(roundOff);
                    addedPassengers.add(nextPassenger);
                    k++;

                } else if (busMatrice[t][j] % 4 == 0) {    //sağ cam kenarındaki yolcuları yerleştirme   // Placing right window side passengers
                    for (int i = 0; i < size; i++) {
                        double total = 0;
                        total += DistanceMatrice[busMatrice[t - 1][j]][i];
                        total += DistanceMatrice[busMatrice[t - 1][j - 1]][i];
                        total += DistanceMatrice[busMatrice[t][j - 1]][i];

                        if (total < sum) {
                            sum = total;
                            nextPassenger = i;
                            busMatrice[t][j] = i;
                        }

                    }

                    for (Integer item : addedPassengers){
                        DistanceMatrice[item][nextPassenger] = 40;
                        DistanceMatrice[nextPassenger][item] =  40;

                    }

                    double roundOff = Math.round(sum * 100.0) / 100.0;
                    distancesSum.add(roundOff);
                    addedPassengers.add(nextPassenger);
                    k++;

                }
            }
        }
    }

    //Yolcuların tüm bilgilerini ve bütün uzaklıklar toplamını yazdıran metot
    // The method that prints all infos about passengers and all distances sum:
    public static void printPassengerInfos(int[][] busMatrice,String []names){
        System.out.println("first value is seat number,            second value is passenger no,            third value is passenger name:");
        int counter = 1;
        for (int i = 0;i<10; i++){
            for (int j = 0; j<4 ; j++){
                System.out.printf("%.40s","   "+counter + ": " +busMatrice[i][j]+ "  " +names[busMatrice[i][j]]+"            ");
                counter++;
            }
            System.out.println();
        }
        System.out.println();
    }

}
