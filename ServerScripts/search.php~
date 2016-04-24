<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($jobname)
		{
			
				$query = "select * from jobs where job_name like '%".$jobname."%'";
				$result = mysqli_query($this -> connection, $query);
				$json = array();

 
			

			if(mysqli_num_rows($result)){
				while($row=mysqli_fetch_assoc($result)){
					$json['job_search'][]=$row;
		                        	}
				}
			
			echo json_encode($json);
			 	mysqli_close($this -> connection);


	
			}	
		}
		

	
	
	$user = new User();
	if(isset($_GET['keyword']))
	{
	$jobname = $_GET['keyword'];

	$user-> does_user_exist($jobname);
			

		
	}
?>