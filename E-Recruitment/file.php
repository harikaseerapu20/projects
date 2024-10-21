<?php
session_start();
include('db_connection.php');

if (isset($_POST['login'])) {
    $username = $_POST['username'];
    $password = md5($_POST['password']); // MD5 for encryption, consider better hashing like bcrypt
    
    $query = "SELECT * FROM users WHERE username='$username' AND password='$password'";
    $result = mysqli_query($conn, $query);
    
    if (mysqli_num_rows($result) == 1) {
        $_SESSION['username'] = $username;
        header('Location: dashboard.php');
    } else {
        echo "Invalid login credentials.";
    }
}
?>

<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include('db_connection.php');
    
    $title = $_POST['job_title'];
    $desc = $_POST['job_desc'];
    $location = $_POST['location'];
    $experience = $_POST['experience'];
    
    $query = "INSERT INTO jobs (title, description, location, experience) VALUES ('$title', '$desc', '$location', '$experience')";
    if (mysqli_query($conn, $query)) {
        echo "Job posted successfully!";
    } else {
        echo "Error: " . mysqli_error($conn);
    }
}
?>

// search_jobs.php
<?php
include('db_connection.php');

if (isset($_GET['keyword'])) {
    $keyword = $_GET['keyword'];
    $query = "SELECT * FROM jobs WHERE title LIKE '%$keyword%' OR description LIKE '%$keyword%'";
    $result = mysqli_query($conn, $query);

    while ($row = mysqli_fetch_assoc($result)) {
        echo "<div>" . $row['title'] . " - " . $row['location'] . "</div>";
    }
}
?>
