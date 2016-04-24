<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($job_id)
		{
			


				$query = "SELECT * FROM jobs INNER JOIN users ON jobs.user_id=users.User_ID WHERE job_id=$job_id";
				$result = mysqli_query($this -> connection, $query);
				$json = array();
				


			

			if(mysqli_num_rows($result)){
				while($row=mysqli_fetch_assoc($result)){
					$json['job_detail'][]=$row;
		                        	}

				}
			
			echo json_encode($json);
			 mysqli_close($this -> connection);

		 }


}




		
	
	$user = new User();
	

	$job_id = $_GET['job_id'];


	$user-> does_user_exist($job_id);
			

		
?>