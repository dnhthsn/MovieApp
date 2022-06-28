package com.example.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.movieapp.R;
import com.example.movieapp.adapter.BannerMoviesPagerAdapter;
import com.example.movieapp.adapter.MainRecyclerAdapter;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.CategoryItem;
import com.example.movieapp.model.Movies;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TabLayout indicatorTab, categoryTab;
    private ViewPager bannerMoviesViewPager;
    private RecyclerView mainRecycler;

    private MainRecyclerAdapter mainRecyclerAdapter;
    private BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    private List<AllCategory> allCategories;
    private List<Movies> homeBanners;
    private List<Movies> tvShowBanners;
    private List<Movies> movieBanners;
    private List<Movies> kidsBanners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tablayout);

        homeBanners = new ArrayList<>();
//        homeBannerList.add(new Movies(1, "Harry Potter and the Deathly hallows", "https://cdn02.ticketbox.vn/poster/9e16af35-831d-11eb-8c61-0242ac110006", ""));
//        homeBannerList.add(new Movies(2, "Spider-man: No way home", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/12/16/800500-1-1639669631931545455159.jpg", ""));
//        homeBannerList.add(new Movies(3, "Shang-chi and the legends of the ten rings", "https://img.meophim.tv/uploads/movies/shang-chi-va-huyen-thoai-thap-luan-thumb.jpg", ""));
//        homeBannerList.add(new Movies(4, "Eternals", "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/e/t/eternals_-_vietnamese_poster_1_.jpg", ""));
//        homeBannerList.add(new Movies(5, "Venom: Let there be Carnage", "https://image.thanhnien.vn/w1024/Uploaded/2022/ygtmjz/2021_05_11/venomposter_tdet.jpg", ""));

        tvShowBanners = new ArrayList<>();
//        tvShowBannerList.add(new Movies(1, "Peacemaker", "https://image.tmdb.org/t/p/w780/hE3LRZAY84fG19a18pzpkZERjTE.jpg", ""));
//        tvShowBannerList.add(new Movies(2, "WandaVision", "https://vcdn-giaitri.vnecdn.net/2020/09/21/WandaVision-Poster-1-9215-1600667511.jpg", ""));
//        tvShowBannerList.add(new Movies(3, "Hawkeye", "https://static.247phim.com/163640/conversions/619eebf77a516_UhhYYAm-435_627.jpg", ""));
//        tvShowBannerList.add(new Movies(4, "The falcon and the winter soldier", "https://thegioidienanh.vn/stores/news_dataimages/anhvu/032021/21/21/5205_05.jpg?rt=20210321215231", ""));
//        tvShowBannerList.add(new Movies(5, "Loki", "https://m.media-amazon.com/images/M/MV5BNTkwOTE1ZDYtODQ3Yy00YTYwLTg0YWQtYmVkNmFjNGZlYmRiXkEyXkFqcGdeQXVyNTc4MjczMTM@._V1_.jpg", ""));

        movieBanners = new ArrayList<>();
//        movieBannerList.add(new Movies(1, "Spider-man: No way home", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/12/16/800500-1-1639669631931545455159.jpg", ""));
//        movieBannerList.add(new Movies(2, "Shang-chi and the legends of the ten rings", "https://img.meophim.tv/uploads/movies/shang-chi-va-huyen-thoai-thap-luan-thumb.jpg", ""));
//        movieBannerList.add(new Movies(3, "Eternals", "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/e/t/eternals_-_vietnamese_poster_1_.jpg", ""));
//        movieBannerList.add(new Movies(4, "Venom: Let there be Carnage", "https://image.thanhnien.vn/w1024/Uploaded/2022/ygtmjz/2021_05_11/venomposter_tdet.jpg", ""));
//        movieBannerList.add(new Movies(5, "Doctor Strange in the Multiverse of Madness", "https://ecdn.game4v.com/g4v-content/uploads/2021/04/doctor-strange-2-1-game4v.jpg", ""));


        kidsBanners = new ArrayList<>();
//        kidsBannerList.add(new Movies(1, "Encanto", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2022/2/4/photo-1-16439542475901828703509.jpg", "https://youtu.be/jKKrfr4To14"));
//        kidsBannerList.add(new Movies(2, "Doraemon: Stand by me", "https://upload.wikimedia.org/wikipedia/vi/6/60/Stand_by_Me_Doraemon_vn_poster.jpg", ""));
//        kidsBannerList.add(new Movies(3, "Doraemon: Stand by me 2", "https://phimnhua.com/wp-content/uploads/2021/08/Stand-By-Me-Doraemon-2-2020.jpg", ""));
//        kidsBannerList.add(new Movies(4, "Minions 2: Rise of Gru", "https://movies.universalpictures.com/media/01-mrg-dm-mainstage-mobile-banner-1080x793-km-f01-031121-6050f69f04ec8-1.jpg", ""));
//        kidsBannerList.add(new Movies(5, "Sing 2", "https://bloganchoi.com/wp-content/uploads/2022/01/review-sing-2-dau-truong-am-nhac-2-1.jpg", ""));


        setBannerMoviesPagerAdapter(homeBanners);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setBannerMoviesPagerAdapter(tvShowBanners);
                        return;
                    case 2:
                        setBannerMoviesPagerAdapter(movieBanners);
                        return;
                    case 3:
                        setBannerMoviesPagerAdapter(kidsBanners);
                        return;
                    default:
                        setBannerMoviesPagerAdapter(homeBanners);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<Movies> homeCatListItem1 = new ArrayList<>();
//        homeCatListItem1.add(new CategoryItem(1, "Squid game", "https://media-cdn.laodong.vn/storage/newsportal/2021/9/29/958751/Squid-Game-Tro-Choi-.jpg", ""));
//        homeCatListItem1.add(new CategoryItem(2, "Detention", "https://upload.wikimedia.org/wikipedia/vi/f/f2/Detention_2019_movie_poster.jpg", ""));
//        homeCatListItem1.add(new CategoryItem(3, "The Unncanny Counter", "https://static.247phim.com/157841/conversions/5fc4a0c01c728_the-uncanny-counter-d-435_627.jpg", ""));
//        homeCatListItem1.add(new CategoryItem(4, "Red notice", "https://www.elleman.vn/wp-content/uploads/2021/12/06/207758/review-phim-red-notice-elle-man-3.jpg", ""));
//        homeCatListItem1.add(new CategoryItem(5, "Hell bound", "https://media.vov.vn/sites/default/files/styles/large/public/2021-11/https-hypebeast-com-image-2021-10-netflix-hellbound-teaser-poster-image-001.jpeg", ""));


        List<Movies> homeCatListItem2 = new ArrayList<>();
//        homeCatListItem2.add(new CategoryItem(1, "Encanto", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2022/2/4/photo-1-16439542475901828703509.jpg", "https://drive.google.com/file/d/1SIJSJUrbTLbDdGsJfv9CuIqxbjW5CHdO/view?usp=sharing"));
//        homeCatListItem2.add(new CategoryItem(2, "Kimetsu no yaiba: Mugen train", "https://gamek.mediacdn.vn/133514250583805952/2020/12/9/photo-1-16074855784472112826198.jpg", ""));
//        homeCatListItem2.add(new CategoryItem(3, "Doraemon: Stand by me 2", "https://phimnhua.com/wp-content/uploads/2021/08/Stand-By-Me-Doraemon-2-2020.jpg", ""));
//        homeCatListItem2.add(new CategoryItem(4, "Jumanji", "https://upload.wikimedia.org/wikipedia/vi/2/2e/Jumanji_Trò_chơi_kỳ_ảo.jpg", ""));
//        homeCatListItem2.add(new CategoryItem(5, "Love and monsters", "https://blog.fshare.vn/wp-content/uploads/2020/10/LoveAndMonstersPoster.jpeg", ""));


        List<Movies> homeCatListItem3 = new ArrayList<>();
//        homeCatListItem3.add(new CategoryItem(1, "Spider-man: No way home", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/12/16/800500-1-1639669631931545455159.jpg", ""));
//        homeCatListItem3.add(new CategoryItem(2, "The Suicide squad", "https://kenh14cdn.com/203336854389633024/2021/8/12/photo-1-16283157586671031712287-1628759880796965255961.jpeg", ""));
//        homeCatListItem3.add(new CategoryItem(3, "Venom: Let there be carnage", "https://image.thanhnien.vn/w1024/Uploaded/2022/ygtmjz/2021_05_11/venomposter_tdet.jpg", ""));
//        homeCatListItem3.add(new CategoryItem(4, "Eternals", "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/e/t/eternals_-_vietnamese_poster_1_.jpg", ""));
//        homeCatListItem3.add(new CategoryItem(5, "Shang-chi and the legend of the ten rings", "https://img.meophim.tv/uploads/movies/shang-chi-va-huyen-thoai-thap-luan-thumb.jpg", ""));


        List<Movies> homeCatListItem4 = new ArrayList<>();
//        homeCatListItem4.add(new CategoryItem(1, "V/H/S", "https://upload.wikimedia.org/wikipedia/en/thumb/c/cd/Vhs-film-poster.jpg/220px-Vhs-film-poster.jpg", ""));
//        homeCatListItem4.add(new CategoryItem(2, "The medium", "https://media.viezone.vn/prod/2021/10/6/image_626c4d6ac3.png", ""));
//        homeCatListItem4.add(new CategoryItem(3, "All of us are dead", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2022/1/30/photo-1-1643529779040757791177.jpg", ""));
//        homeCatListItem4.add(new CategoryItem(4, "Friday the 13th (2009)", "https://upload.wikimedia.org/wikipedia/vi/d/d7/Fridaythe13th2009.JPG", "https://meophim.tv/phim/thu-6-ngay-13-2/tap-full-sv0"));
//        homeCatListItem4.add(new CategoryItem(5, "The cabin in the woods", "https://upload.wikimedia.org/wikipedia/vi/thumb/b/b8/CitwTeaserSmall.jpg/220px-CitwTeaserSmall.jpg", ""));


        allCategories = new ArrayList<>();
//        allCategories.add(new AllCategory(1, "Only on Zetflix", homeCatListItem1));
//        allCategories.add(new AllCategory(2, "Kids and family movies", homeCatListItem2));
//        allCategories.add(new AllCategory(3, "Action movies", homeCatListItem3));
//        allCategories.add(new AllCategory(4, "Horror movies", homeCatListItem4));


        setMainRecycler(allCategories);
    }

    private void setBannerMoviesPagerAdapter(List<Movies> movies) {
        bannerMoviesViewPager = findViewById(R.id.banner_ViewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(movies);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 5000, 7000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);
    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(() -> {
                if (bannerMoviesViewPager.getCurrentItem() < homeBanners.size() - 1) {
                    bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                } else {
                    bannerMoviesViewPager.setCurrentItem(0);
                }
            });
        }


    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {

        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }
}