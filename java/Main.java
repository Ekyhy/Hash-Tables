
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class HashTable {

    private final int SIZE = 211;
    private Integer[] table;
    private final Integer DELETED = Integer.MIN_VALUE;

    public HashTable() {
        table = new Integer[SIZE];
    }

    // Fungsi hash
    private int hashFunction(int key) {
        return key % SIZE;
    }

    // Insert data
    public void insert(int key) {
        int index = hashFunction(key);
        int originalIndex = index;

        while (table[index] != null && table[index] != DELETED) {

            if (table[index] == key) {
                System.out.println("Data " + key + " sudah ada!");
                return;
            }

            // Linear probing
            index = (index + 1) % SIZE;

            if (index == originalIndex) {
                System.out.println("Hash Table penuh!");
                return;
            }
        }

        table[index] = key;
        System.out.println("Data " + key + " berhasil ditambahkan.");
    }

    // Search data
    public int search(int key) {
        int index = hashFunction(key);
        int originalIndex = index;

        while (table[index] != null) {

            if (table[index] != DELETED && table[index] == key) {
                return index;
            }

            index = (index + 1) % SIZE;

            if (index == originalIndex) {
                break;
            }
        }

        return -1;
    }

    // Delete data
    public void delete(int key) {
        int position = search(key);

        if (position == -1) {
            System.out.println("Data " + key + " tidak ditemukan.");
            return;
        }

        table[position] = DELETED;
        System.out.println("Data " + key + " berhasil dihapus.");
    }

    // Display data
    public void display() {
        System.out.println("\nIsi Hash Table:");

        for (int i = 0; i < SIZE; i++) {

            if (table[i] != null && table[i] != DELETED) {
                System.out.println("Index " + i + " : " + table[i]);
            }
        }
    }

    // Load data dari CSV
    public void loadData(String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                int id = Integer.parseInt(values[0].trim());

                insert(id);
            }

            System.out.println("Data dari file berhasil dimuat!");

        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        HashTable hashTable = new HashTable();

        System.out.println("===== PROGRAM HASH TABLE =====");

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Input Data");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Data");
            System.out.println("5. Load Data Dari CSV");
            System.out.println("6. Keluar");

            System.out.print("Pilih menu: ");
            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Masukkan angka: ");
                    int insertData = input.nextInt();
                    hashTable.insert(insertData);
                    break;

                case 2:
                    System.out.print("Masukkan angka yang ingin dihapus: ");
                    int deleteData = input.nextInt();
                    hashTable.delete(deleteData);
                    break;

                case 3:
                    System.out.print("Masukkan angka yang dicari: ");
                    int searchData = input.nextInt();

                    int result = hashTable.search(searchData);

                    if (result != -1) {
                        System.out.println("Data ditemukan pada index " + result);
                    } else {
                        System.out.println("Data tidak ditemukan");
                    }

                    break;

                case 4:
                    hashTable.display();
                    break;

                case 5:
                    input.nextLine();

                    System.out.print("Masukkan nama file CSV: ");
                    String filename = input.nextLine();

                    hashTable.loadData(filename);
                    break;

                case 6:
                    System.out.println("Program selesai.");
                    input.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}