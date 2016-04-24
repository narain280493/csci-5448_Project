<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($email,$jobname,$jobpay,$jobdesc)
		{
			$query1= "Select user_id from users where email = '$email'";
			$result1 = mysqli_query($this->connection, $query1);
			$row = mysqli_fetch_assoc($result1);
			$userid=$row['user_id'];

			$query = "Insert into jobs (job_id,job_name,job_description,job_type,pay,user_id,job_posted_date) values ('','$jobname','$jobdesc','Studentopportunity','$jobpay','$userid',CURRENT_TIMESTAMP)";
			$result = mysqli_query($this->connection, $query);


				if($result == 1 ){
					$json['success'] = 'Job Posted Successfully';
				}else{
					$json['error'] = 'Failed to post job';
				}
				echo json_encode($json);
				mysqli_close($this->connection);


/*

			if(mysqli_num_rows($result)>0){
				$json['success'] = ' Welcome '.$email;
				echo json_encode($json);
				mysqli_close($this -> connection);
			}else{
				$json['error'] = ' Please check your credentials or Register ';
				echo json_encode($json);
				mysqli_close($this -> connection);	mysqli_close($this -> connection);
			}
			*/
		}
		
	}
	
	
	$user = new User();

		$email = $_POST['email'];
		$jobname = $_POST['jobname'];
		$jobpay = $_POST['jobpay'];
		$jobdesc = $_POST['jobdesc'];
		

			$user-> does_user_exist($email,$jobname,$jobpay,$jobdesc);

		
	
?>