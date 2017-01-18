var app = angular.module('myApp', []);

app.service('StudentService', ['$http', '$q',
function($http, $q) {
    return {
        findAllStudents: function() {
            return $http.get('http://localhost:8080/SpringmvcJap/s/findAll').then(function(response) {
                return response.data;
            },
            function(errResponse) {
                return $q.reject(errResponse);
            },function(value){
            	console.log("notify");
            });
        },
        createStudent: function(student) {
            return $http.post("http://localhost:8080/SpringmvcJap/s/create", student).then(function(value) {
                return value.data;
            },
            function(reason) {
                return $q.reject(reason);
            });
        },

        updateStudent: function(student, id) {
            return $http.put("http://localhost:8080/SpringmvcJap/s/update/" + id, student).then(function(value) {
                return value.data;
            },
            function(reason) {
                return $q.reject(reason);
            });
        },
        deleteStudent: function(id) {
            return $http.delete('http://localhost:8080/SpringmvcJap/s/delete/' + id).then(function(value) {
                return value.data;
            },
            function(reason) {
                return $q.reject(reason);
            });
        }

    };
}]);

app.controller('StudentController', ['$scope', 'StudentService',
function($scope, StudentService) {
    var self = this;
    self.student = {
        id: null,
        name: '',
        age: null,
        birth: null
    };

    self.students = [];

    self.fetchAllStudents = function() {
        StudentService.findAllStudents().then(function(value) {
            self.students = value;
            console.dir("enter find all function");
        },
        function(reason) {
            console.error('Error when findAll Studnet');
        })
    };

    // invoke method
    self.fetchAllStudents();

    self.createStudent = function(student) {
        StudentService.createStudent(student).then(self.fetchAllStudents,
        function(reason) {
            console.dir("create student error");
        });
    };

    self.updateStudent = function(student, id) {
        StudentService.updateStudent(student, id).then(self.fetchAllStudents,
        function(reason) {
            console.dir("update student error");
        });
    };

    self.deleteStudent = function(id) {
        StudentService.deleteStudent(id).then(self.fetchAllStudents,
        function(reason) {
            console.dir("delete student error");
        });
    };

    self.submit = function() {
        if (self.student.id == null) {
            console.log('save student', self.student);
            self.createStudent(self.student);
        } else {
            self.updateStudent(self.student, self.student.id);
            console.log("update student", self.student);
        }
        self.reset();
    };

    self.edit = function(id) {
        console.log('id to be edit', id);
        for (var i = 0; i < self.students.length; i++) {
            if (self.students[i].id == id) {
                self.student = angular.copy(self.students[i]);
                break;
            }
        }
    };

    self.remove = function(id) {
        console.log('id to be remove', id);
        for (var i = 0; i < self.students.length; i++) {
            if (self.students[i].id == id) {
                self.reset();
                break;
            }
        }
        self.deleteStudent(id);
    };

    self.reset = function() {
        self.student = {
            id: null,
            name: '',
            age: null,
            birth: null
        };
        $scope.myForm.$setPristine();
    };

}]);