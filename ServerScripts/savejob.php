<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($email,$job_id)
		{
			$query1= "Select user_id from users where email = '$email'";
			$result1 = mysqli_query($this->connection, $query1);
			$row = mysqli_fetch_assoc($result1);
			$userid=$row['user_id'];
			$query = "Insert into savedjobs (user_id,job_id) values ('$userid','$job_id')";
			$result = mysqli_query($this->connection, $query);


				if($result == 1 ){
					$json['success'] = 'Saved job Successfully';
				}else{
					$json['error'] = 'Failed to Save job';
				}
				echo json_encode($json);
				mysqli_close($this->connection);


		}
		
	}
	
	
	$user = new User();

		$job_id = $_GET['job_id'];
		$email = $_GET['email'];

		

			$user-> does_user_exist($email,$job_id);

		
	
?>