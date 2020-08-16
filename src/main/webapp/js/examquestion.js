var department = [];
	var designation = [];


	function changeDept(e,elem){
		var deptString;
		if(elem.checked){
			department.push(elem.value);
		}else{
			department = department.filter(function(dept){
				return dept !== elem.value;
			})
		}
		department.forEach(function(dept){
			deptString = deptString ? deptString + '|' + dept : dept;
		})
		document.forms["Form"]["deptIds"].value = deptString === undefined ? "" : deptString;
	}

	function changeDesign(e,elem){
		var designString;
		if(elem.checked){
			designation.push(elem.value);
		}else{
			designation = designation.filter(function(design){
				return design !== elem.value;
			})
		}
		designation.forEach(function(design){
			designString = designString ? designString + '|' + design : design;
		})
		document.forms["Form"]["designIds"].value = designString === undefined ? "" : designString;
	}
	function validateForm(){
		if($("#designation").val() === "" || $("#department").val() === ""){
			alert($("#designation").val() === "" ? "Choose one designation" : "Choose one department");
			return false;	
		}
		return true;
	}
	function getDepartmentById(deptId){
		const ids = deptId.split('|');
		return departmentObj.filter((dept) => ids.find((id) => id== dept.deptId)).map(dept => dept.deptName);
	}
	