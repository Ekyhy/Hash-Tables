# Hash Table (Python) — Linear Probing + Tombstone

Program ini mengimplementasikan **Hash Table** untuk menyimpan **bilangan bulat (integer)** menggunakan teknik:
- **Hashing**: `index = key % size`
- **Collision handling**: **Linear Probing**
- **Delete**: **tombstone** (penanda `DELETED`) supaya proses `search()` tetap benar

Kodenya berada di: `python/hash.py`.

---

## Daftar Isi
- [Fitur](#fitur)
- [Konsep Utama](#konsep-utama)
- [Cara Menjalankan](#cara-menjalankan)
- [Format Data (CSV)](#format-data-csv)
- [Menu Program](#menu-program)
- [Penanganan Collision](#penanganan-collision)
- [Penanganan Delete (Tombstone)](#penanganan-delete-tombstone)
- [Referensi](#referensi)

---

## Fitur
1. **Insert** nilai integer ke dalam hash table.
2. **Search** nilai berdasarkan key.
3. **Delete** nilai tanpa merusak rangkaian collision.
4. **Display** isi hash table (yang valid saja).
5. **Load data** dari file **CSV/Excel** (berdasarkan kolom `id`).
6. Menu interaktif berbasis input user.

---

## Konsep Utama
### 1) Hashing
Setiap `key` dipetakan ke index awal:
```python
index = key % size
```

### 2) Collision Handling: Linear Probing
Jika slot index awal sudah terisi, program mencari slot berikutnya secara berurutan:
```python
index = (index + 1) % size
```
Proses ini berulang hingga:
- menemukan slot kosong, atau
- menemukan key yang sama (untuk mencegah duplikasi), atau
- kembali ke index awal (tabel penuh).

### 3) Delete dengan Tombstone
Pada open addressing, mengosongkan slot jadi `None` saat delete bisa membuat `search()` berhenti lebih awal.
Karena itu, slot yang terhapus ditandai menggunakan tombstone:
```python
DELETED = "__DELETED__"
```
Saat `search()`, slot bertombstone **tidak dianggap kosong**.

---

## Cara Menjalankan
Masuk ke folder python, lalu jalankan:

```bash
python hash.py
```

Setelah itu, program akan menampilkan **menu interaktif**.

> Catatan: Program memakai package `pandas` untuk load CSV/Excel.

---

## Format Data (CSV)
File contoh ada di:
- `python/data_numerik.csv`

Format yang dibutuhkan:
- CSV memiliki header kolom **`id`**
- Isi berupa angka integer

Contoh struktur:
```csv
id
5288
5993
8689
...
```

---

## Menu Program
Dalam `hash.py`, menu yang tersedia:
1. **Input Data** → `insert(angka)`
2. **Hapus Data** → `delete(angka)`
3. **Cari Data** → `search(angka)` dan tampilkan hasil index
4. **Tampilkan Data** → `display()`
5. **Load Data Dari File (.csv)**
6. **Keluar**

---

## Penanganan Collision
Collision terjadi saat dua key berbeda menghasilkan **index hash yang sama**.
Di program ini, collision ditangani oleh bagian berikut pada method `insert()`:

- Program memulai dari index hasil hash
- Jika slot tidak kosong, ia bergerak ke index berikutnya menggunakan **linear probing**
- Sampai menemukan slot kosong atau menentukan bahwa key sudah ada

Dengan ini, hash table tetap bisa menyimpan banyak data walau terjadi tabrakan.

---

## Penanganan Delete (Tombstone)
Bagian tombstone ada pada:
- konstanta `DELETED = "__DELETED__"`
- method `delete()` yang mengganti slot dengan `DELETED`
- method `search()` yang mengabaikan tombstone saat membandingkan key, tapi **tetap melanjutkan probing**

Alhasil:
- setelah penghapusan, `search()` tidak salah berhenti
- collision chain tetap terjaga

---

## Referensi
- Konsep Hash Table: hashing & collision resolution
- Open Addressing (Linear Probing)
- Tombstone untuk deletion pada open addressing

---

## Struktur Folder
- `hash.py` → implementasi program
- `data_numerik.csv` → data contoh untuk load
- `README.md` → dokumentasi

