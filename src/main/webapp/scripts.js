
function showCurrentTime() {
    const now = new Date();
    const timeString = now.toLocaleTimeString();
    document.getElementById("current-time").innerText = "Current Time: " + timeString;
    setTimeout(showCurrentTime, 1000);
}
function showCurrentTime() {
    setInterval(function() {
        var currentTime = new Date();
        var hours = currentTime.getHours();
        var minutes = currentTime.getMinutes();
        var seconds = currentTime.getSeconds();

        // Format time as HH:MM:SS
        if (minutes < 10) minutes = '0' + minutes;
        if (seconds < 10) seconds = '0' + seconds;

        var timeString = hours + ':' + minutes + ':' + seconds;
        document.getElementById("current-time").innerHTML = timeString;
    }, 1000); // Update every second
}
		

document.addEventListener("DOMContentLoaded", function () {
        const images = document.querySelectorAll(".gallery-image");
        if (images.length === 0) {
            console.error("No images found with class '.gallery-image'.");
            return;
        }

        let currentIndex = 0;

        function showNextImage() {
            // Remove active class from current image
            images[currentIndex].classList.remove("active");

            // Move to next image (loop back to the first if at the end)
            currentIndex = (currentIndex + 1) % images.length;

            // Add active class to the next image
            images[currentIndex].classList.add("active");
        }

        // Initialize: Show the first image
        images[currentIndex].classList.add("active");

        // Start carousel
        setInterval(showNextImage, 3000);
    });