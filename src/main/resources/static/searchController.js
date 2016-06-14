app.controller("searchController", function($scope, $http) {

    window.scope = $scope;

    $scope.search = function () {
        var searchURL = "http://localhost:8080/subject/search/person?";
        if ($scope.search.firstName) {
            searchURL = searchURL + "&firstName=" + $scope.search.firstName;
        }
        if ($scope.search.lastName) {
            searchURL = searchURL + "&lastName=" + $scope.search.lastName;
        }
        if ($scope.search.identityCode) {
            searchURL = searchURL + "&identityCode=" + $scope.search.identityCode;
        }
        if ($scope.search.birthDate) {
            searchURL = searchURL + "&birthDate=" + $scope.search.birthDate;
        }

        $http.get(searchURL)
            .then(function(response) {
                $scope.subjects = response.data;
            });
    };
    
    
    
});