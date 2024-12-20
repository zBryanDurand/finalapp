package pe.idat.appinstituto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.idat.appinstituto.db.dao.AlumnoDao
import pe.idat.appinstituto.db.entity.AlumnoEntity


@Database(entities = [AlumnoEntity::class],
    version = 1)
abstract class InstitutoRoomDatabase : RoomDatabase() {

    abstract fun alumnoDao(): AlumnoDao

    companion object{

        @Volatile
        private var INSTANCIA : InstitutoRoomDatabase? = null

        fun getDatabase(context: Context): InstitutoRoomDatabase{
            val temInstancia = INSTANCIA
            if(temInstancia != null){
                return temInstancia
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InstitutoRoomDatabase::class.java,
                    "idatdb"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCIA = instance
                return instance
            }
        }
    }
}