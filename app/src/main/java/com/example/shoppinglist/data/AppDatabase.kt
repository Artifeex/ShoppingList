package com.example.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

//entities = перечисляем все наши таблицы
//version = нужно для того, чтобы регулировать версии. Если добавляем новые таблицы или как-то изменяем старые, то нужно обновлять версии
//exportSchema = нужна для того, чтобы на устройстве хранилась история версий БД. Инога без это
@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object {
        //Это будет объект типа, который реализует для нас Room. И так как AppDatabase - это родительский, то ему можно присваивать потомков(то, что реализует ROom)
        private var INSTANCE: AppDatabase? = null
        //Нужен для того, чтобы заблокировать создание нескольких экземляров из-за нескольких потоков. Т.е. когда мы одним потом забрали LOCK в synchronized
        //то другой поток не может зайти внутри synchronized, пока lock принадлежит другому потоку. Как только поток завершит свою работу, LOCK так же освободится
        //и другой поток сможем зайти внутри метода synchronized.
        private val LOCK = Any()

        private const val DB_NAME = "shop_items.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                //дополнительная проверка нужна для того, чтобы, если 1-ый поток создал экземпляр, то второй поток, который ждал, когда LOCK освободится,
                // не создал еще один экземпляр, а использовал уже готовый.
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}