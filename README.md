# AntwhaleImageSlider

AntwhaleImageSlider is CustomView to provide showing infinite sliding images.

You can use it with a little code.

<img src = "https://github.com/antwhale/AntwhaleImageSlider/assets/85996753/2c8d3a05-576f-4408-ba40-511d58556027">


## Usages

### 1️⃣ Add dependency

app/build.gradle

    implementation 'io.github.antwhale:AntwhaleImageSlider:1.0.0'
    
### 2️⃣ Define XML

    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <io.github.antwhale.AntwhaleImageSlider
            android:id="@+id/imageSlider"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:bottomMargin="16dp"
            app:indicatorMargin="4dp"
            app:activeIndicatorRes="@drawable/indicator_active"
            app:inactiveIndicatorRes="@drawable/indicator_inactive"
            app:autoScrolling="true"
            app:scrollingDelay="3000"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"/>
            
    </androidx.constraintlayout.widget.ConstraintLayout>
    
### 3️⃣ Define adapter class

    class RollingBannerAdapter(
    override val sliderImages: List<String>
    ) : AntwhaleImageSliderAdapter<String, RollingBannerAdapter.RollingBannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RollingBannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RollingBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RollingBannerViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(sliderImages[getRealPosition(position)])
            .into(holder.imageView)
    }

    override fun getItemListSize(): Int = sliderImages.size

        inner class RollingBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        }
    }
    
### 4️⃣ Connect adapter with AntwhalImageSlider

    val antwhaleImageSlider = findViewById<AntwhaleImageSlider>(R.id.imageSlider)
        
        val images = listOf(
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/03/08/21/41/landscape-4913841_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg"
        )

        val rollingBannerAdapter = RollingBannerAdapter(images)
        antwhaleImageSlider.setAdapter(rollingBannerAdapter)
        
