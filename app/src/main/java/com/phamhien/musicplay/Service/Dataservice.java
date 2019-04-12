package com.phamhien.musicplay.Service;

import com.phamhien.musicplay.Model.Album;
import com.phamhien.musicplay.Model.Baihat;
import com.phamhien.musicplay.Model.ChuDe;
import com.phamhien.musicplay.Model.Playlist;
import com.phamhien.musicplay.Model.QuangCao;
import com.phamhien.musicplay.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner ();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GETPlaylistCurrentDay();

    @GET("chude.php")
    Call<List<ChuDe>> GETChudeCurrentDay();


    @GET("chudevathelaoi.php")
    Call<Theloaitrongngay> GETCategorMusic();


    @GET("albumhot.php")
    Call<List<Album>> GETAlbumHot();

    @GET("bhyeuthich.php")
    Call<List<Baihat>> GETBaihat();

    @FormUrlEncoded
    @POST("dsbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihatquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("dsbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihatplaylist(@Field("idplaylist") String idplaylist);

    @GET("dsplaylist.php")
    Call<List<Playlist>> GETdsplaylist();

    @GET("dschude.php")
    Call<List<ChuDe>> GETchude();

    @GET("dsalbum.php")
    Call<List<Album>> GETAlbum();

    @FormUrlEncoded
    @POST("dsbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihatalbum( @Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("luotthich.php")
    Call<String> Updateluotthich(@Field("luotthich") String luotthich ,@Field("idbaihat") String idbahatl);


    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<Baihat>> GetSearchbaihat(@Field("tukhoa") String tukhoa);

}
