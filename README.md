# Hello Koin!
### Sample Project with Koin 2.0 and JUnit Test.


#### Sample to load image With Picasso
```
imageUrl.let {
    loadingProgressBar.isVisible = true
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.some_image_placeholder)
        .error(R.drawable.some_error_image)
        .into(myImageView, object : Callback {
            override fun onSuccess() {
                loadingProgressBar.isVisible = false
            }
            override fun onError(e: Exception?) {
                loadingProgressBar.isVisible = false
            }
        })
}
```