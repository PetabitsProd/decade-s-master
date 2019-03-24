package fr.agopiantexier.decadesmaster.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import fr.agopiantexier.decadesmaster.model.Artists;
import fr.agopiantexier.decadesmaster.model.Playlist;
import fr.agopiantexier.decadesmaster.model.Sing;
import fr.agopiantexier.decadesmaster.model.Song;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "DecadesMasterDB.sqlite";
    private static final int DB_VERSION = 1;

    public  DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTableIfNotExists(connectionSource, Playlist.class);
            TableUtils.createTableIfNotExists(connectionSource, Artists.class);

            TableUtils.createTableIfNotExists(connectionSource, Song.class);
            TableUtils.createTableIfNotExists(connectionSource, Sing.class);
            Log.i( "DATABASE", "onCreate invoked" );

        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e( "DATABASE", "Can't create Database", e );

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable( connectionSource, Playlist.class, true );
            TableUtils.dropTable( connectionSource, Artists.class, true );

            TableUtils.dropTable( connectionSource, Song.class, true );
            TableUtils.dropTable( connectionSource, Sing.class, true );
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }


    public void insertArtists ( Artists artists ) {
        try {
            Dao<Artists, Integer> dao = getDao( Artists.class );
            dao.create( artists );
            Log.i( "DATABASE", "insertScore invoked" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't insert score into Database", exception );
        }
    }


    public void insertPlaylist ( Playlist playlist ) {
        try {
            Dao<Playlist, Integer> dao = getDao( Playlist.class );
            dao.create( playlist );
            Log.i( "DATABASE", "insertplaylist invoked" );
        } catch( Exception exception ) {
            Log.e( "DATABASE", "Can't insert playlkst into Database", exception );
        }
    }


    public void deleteAllPlaylist(){
        try {
            Dao<Playlist, Integer> dao = getDao( Playlist.class );
            TableUtils.clearTable(connectionSource, Playlist.class);
        }catch (Exception e ){
            Log.e( "DATABASE", "Can't delete playlkst into Database", e );

        }

    }

}
