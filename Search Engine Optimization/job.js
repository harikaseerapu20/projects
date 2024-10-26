function searchJobs() {
    var keyword = document.getElementById('search').value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "search_jobs.java?keyword=" + keyword, true);
    xhr.onload = function() {
        if (this.status == 200) {
            document.getElementById('results').innerHTML = this.responseText;
        }
    }
    xhr.send();
}

