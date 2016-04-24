<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($jobname,$minpay,$maxpay,$checkboxoncampusjobs,$checkboxstudentopportunities)
		{
			


			if($checkboxoncampusjobs=="true"&&$checkboxstudentopportunities=="true")

			{



				$query = "select * from jobs where job_name like '%".$jobname."%'and pay>=$minpay and pay<=$maxpay" ;
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
			else if($checkboxoncampusjobs=="true"&&$checkboxstudentopportunities=="false")
				{

				$query = "select * from jobs where job_name like '%".$jobname."%'and job_type='Oncampus' and pay>=$minpay and pay<=$maxpay" ;
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

			else if($checkboxstudentopportunities=="true"&&$checkboxoncampusjobs=="false")
			{


				$query = "select * from jobs where job_name like '%".$jobname."%'and job_type='Studentopportunity' and pay>=$minpay and pay<=$maxpay";
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


			else
			{

				$query = "select * from jobs where job_name like '%".$jobname."%'and pay>=$minpay and pay<=$maxpay" ;
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



}


		
	
	$user = new User();
	

	$jobname = $_GET['keyword'];
	$minpay = $_GET['minpay'];
	$maxpay = $_GET['maxpay'];

	$checkboxoncampusjobs = $_GET['checkboxoncampusjobs'];
	$checkboxstudentopportunities = $_GET['checkboxstudentopportunities'];
	if($minpay=="")
	{
		$minpay=0;
	}
	if($maxpay=="")
	{
		$maxpay=10000;
	}
	//echo $checkboxoncampusjobs;

	$user-> does_user_exist($jobname,$minpay,$maxpay,$checkboxoncampusjobs,$checkboxstudentopportunities);
			

		
?>