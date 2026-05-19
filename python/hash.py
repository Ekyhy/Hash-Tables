import pandas as pd

DELETED = "__DELETED__"


class Numerik:
    def __init__(self, id):
        self.id = id

    def __repr__(self):
        return f"[ID: {self.id}]"


class HashTable:
    def __init__(self, size=211):
        self.size = size
        self.table = [None] * self.size

    # Fungsi hash
    def hash_function(self, key):
        return key % self.size

    # Insert data
    def insert(self, key):
        index = self.hash_function(key)
        original_index = index

        while self.table[index] is not None:
            if self.table[index] == key:
                print(f"Data {key} sudah ada!")
                return

            # Linear Probing
            index = (index + 1) % self.size

            # Jika tabel penuh
            if index == original_index:
                print("Hash Table penuh!")
                return

        self.table[index] = key
        print(f"Data {key} berhasil ditambahkan.")

    # Search data
    def search(self, key):
        index = self.hash_function(key)
        original_index = index

        while self.table[index] is not None:
            if self.table[index] != DELETED and self.table[index] == key:
                return index

            index = (index + 1) % self.size

            if index == original_index:
                break

        return -1

    # Delete data (tombstone untuk linear probing)
    def delete(self, key):
        position = self.search(key)

        if position == -1:
            print(f"Data {key} tidak ditemukan.")
            return

        self.table[position] = DELETED
        print(f"Data {key} berhasil dihapus.")

    # Display table
    def display(self):
        print("\nIsi Hash Table:")
        for i, value in enumerate(self.table):
            if value is not None and value != DELETED:
                print(f"Index {i} : {value}")

    def load_data(self, path):
        if path.endswith('.csv'):
            df = pd.read_csv(path)
        else:
            df = pd.read_excel(path)

        for _, row in df.iterrows():
            self.insert(int(row['id']))

hash_table = HashTable()

print("\n100 data random berhasil dimasukkan ke hash table!")

# Menu Program
while True:
    print("\n===== MENU =====")
    print("1. Input Data")
    print("2. Hapus Data")
    print("3. Cari Data")
    print("4. Tampilkan Data")
    print("5. Load Data Dari File (.csv)")
    print("6. Keluar")

    choice = input("Pilih menu: ")

    if choice == "1":
        data = int(input("Masukkan angka: "))
        hash_table.insert(data)

    elif choice == "2":
        data = int(input("Masukkan angka yang ingin dihapus: "))
        hash_table.delete(data)

    elif choice == "3":
        data = int(input("Masukkan angka yang dicari: "))
        result = hash_table.search(data)

        if result != -1:
            print(f"Data ditemukan pada index {result}")
        else:
            print("Data tidak ditemukan")

    elif choice == "4":
        hash_table.display()

    elif choice == "5":
        # Memuat data dari file CSV atau Excel
        filename = input("Masukkan nama file (contoh: data_barang.csv): ")
        try:
            hash_table.load_data(filename)
            print(f"Data dari {filename} berhasil dimuat!")
        except Exception as e:
            print(f"Gagal memuat file: {e}")

    elif choice == "6":
        print("Program selesai.")
        break

    else:
        print("Pilihan tidak valid!")