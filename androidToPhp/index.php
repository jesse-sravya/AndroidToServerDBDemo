<?php


	$con=mysqli_connect("localhost","root","jesse","collegeWebsite");

	if (mysqli_connect_errno($con)) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	$message = $_POST['message']; // Do something with message

	$result = mysqli_query($con,"SELECT count(*) FROM article");

	$row = mysqli_fetch_array($result);

	$data = $row[0]." ".$message;

	if($data) {
		echo $data;
	}


?>