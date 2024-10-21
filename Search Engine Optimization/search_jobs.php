<?php
include 'db_connection.php';

if (isset($_GET['keyword'])) {
    $keyword = $_GET['keyword'];
    $query = "SELECT * FROM jobs WHERE title LIKE '%$keyword%' OR description LIKE '%$keyword%'";
    $result = mysqli_query($conn, $query);

    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            echo "<div>";
            echo "<h3>" . $row['title'] . "</h3>";
            echo "<p>" . $row['description'] . "</p>";
            echo "<p>Location: " . $row['location'] . "</p>";
            echo "</div>";
        }
    } else {
        echo "No jobs found.";
    }
}
?>
