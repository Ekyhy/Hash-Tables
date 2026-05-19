# Hash Table (Java) — Linear Probing + Tombstone

Implementasi **Hash Table** untuk menyimpan bilangan bulat menggunakan metode **open addressing** dengan teknik **Linear Probing**. Program menyediakan menu interaktif (insert, hapus, cari, tampilkan) dan mampu memuat data otomatis dari file **CSV**.

---

## Isi Repository
- `java/Main.java` : implementasi kelas `HashTable` dan program menu `Main`.
- `java/data_numerik.csv` : contoh format data CSV (kolom `id`).

---

## Konsep yang Digunakan
### 1) Hashing
Setiap `key` dipetakan ke index awal menggunakan:

- `index = key % SIZE`

### 2) Collision Handling: Linear Probing
Jika index awal sudah terisi, program mencari slot kosong dengan langkah:

- `index = (index + 1) % SIZE`

### 3) Delete dengan Tombstone
Saat data dihapus, slot **tidak diubah menjadi `null`**, melainkan ditandai dengan nilai khusus:

- `DELETED = Integer.MIN_VALUE`

Tombstone diperlukan agar pencarian (*search*) tetap dapat melanjutkan probing melewati slot yang sudah dihapus.

---

## Penjelasan Kode `java/Main.java`

### A. Kelas `HashTable`
#### 1) Properti utama
```java
private final int SIZE = 211;
private Integer[] table;
private final Integer DELETED = Integer.MIN_VALUE;
```
- `SIZE` : ukuran tabel.
- `table` : array penampung.
- `DELETED` : penanda tombstone.

#### 2) Fungsi hash
```java
private int hashFunction(int key) {
    return key % SIZE;
}
```
Memberikan index awal untuk key.

#### 3) `insert(int key)`
- Hitung index awal.
- Jika bentrok, lakukan linear probing.
- Jika key sudah ada, tampilkan pesan dan hentikan.
- Jika tabel penuh (mengitari kembali index awal), tampilkan pesan.

#### 4) `search(int key)`
- Probing dari index awal.
- Berhenti jika menemukan `null` (key pasti tidak ada).
- Mengembalikan index jika ditemukan (dan bukan `DELETED`).
- Mengembalikan `-1` jika tidak ditemukan.

#### 5) `delete(int key)`
- Cari posisi key menggunakan `search`.
- Jika ditemukan, set `table[position] = DELETED`.

#### 6) `display()`
Menampilkan semua slot yang valid (bukan `null` dan bukan `DELETED`) beserta index-nya.

#### 7) `loadData(String path)`
- Membuka file CSV menggunakan `BufferedReader`.
- Me-skip baris header.
- Untuk tiap baris:
  - pecah dengan `split(",")`
  - ambil kolom pertama sebagai `id`
  - `insert(id)`.

> File CSV diharapkan memiliki header, dan nilai `id` berada di kolom pertama.

---

### B. Kelas `Main`
Program menampilkan menu:
1. Input Data → `hashTable.insert(angka)`
2. Hapus Data → `hashTable.delete(angka)`
3. Cari Data → `hashTable.search(angka)` lalu tampilkan hasil
4. Tampilkan Data → `hashTable.display()`
5. Load Data Dari CSV → `hashTable.loadData(namaFile)`
6. Keluar

Catatan penting untuk menu CSV:
- setelah `nextInt()`, program memanggil `input.nextLine()` untuk membersihkan newline agar pembacaan nama file (string) benar.

---

## Cara Menjalankan
Contoh kompilasi dan eksekusi dari folder repo:

```bash
javac java/Main.java
java -cp java Main
```

---

## Referensi Konsep
- **Hashing**: pemetaan key ke index.
- **Collision Handling**: linear probing.
- **Tombstone**: penanda delete agar pencarian tetap benar.

