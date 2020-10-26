
package com.codexflow.lastroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [User::class], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): UserRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDatabase::class.java,
                        "user_database"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }

        }

        private class WordDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.userDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(userDao: UserDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            userDao.deleteAll()

            var word = User("İnanç", "Yılmaz", 24)
            userDao.insert(word)
            word = User("İnanç", "Yılmaz", 241)
            userDao.insert(word)
        }
    }

}
