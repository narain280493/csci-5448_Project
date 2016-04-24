<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($email)
		{
			


				$query = "select * from jobs where job_id in (select job_id from appliedjobs where user_id in (SELECT user_id from users where email='$email'))";

				$result = mysqli_query($this -> connection, $query);
				$json = array();
				


			

			if(mysqli_num_rows($result)){
				while($row=mysqli_fetch_assoc($result)){
					$json['appliedjobs'][]=$row;
		                        	}

				}
			
			echo json_encode($json);
			 mysqli_close($this -> connection);

		 }


}




		
	
	$user = new User();
	

	$email = $_GET['email'];


	$user-> does_user_exist($email);
			

		
?>