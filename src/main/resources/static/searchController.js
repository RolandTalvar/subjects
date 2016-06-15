app.controller("searchController", function($scope, $http) {

    window.scope = $scope;

    $scope.clearSearchResults = function() {
        $scope.enterpriseList = null;
        $scope.personList = null;
        $scope.employeeList = null;
        $scope.customerList = null;
    };

    $scope.editSubject = function (subjectType, subjectId) {
        if (subjectType === 1) {
            window.open("http://localhost:8080/subject/editPerson?id=" + subjectId);
        } else if (subjectType === 2) {
            window.open("http://localhost:8080/subject/editEnterprise?id=" + subjectId);
        } else if (subjectType === 3) {
            window.open("http://localhost:8080/subject/editEmployee?id=" + subjectId);
        }
    };

    $scope.search = function () {

        if ($scope.searchType === "all") {
            $scope.searchPerson();
        }
        if ($scope.searchType === "person") {
            $scope.searchPerson();
        }
        if ($scope.searchType === "enterprise") {
            $scope.searchEnterprise();
        }
        if ($scope.searchType === "employee") {
            $scope.searchEmployee();
        }
        if ($scope.searchType === "customer") {
            $scope.searchCustomer();
        }

    };

    $scope.searchPerson = function () {
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

        if ($scope.search.country) {
            searchURL = searchURL + "&country=" + $scope.search.country;
        }
        if ($scope.search.county) {
            searchURL = searchURL + "&county=" + $scope.search.county;
        }
        if ($scope.search.townVillage) {
            searchURL = searchURL + "&townVillage=" + $scope.search.townVillage;
        }
        if ($scope.search.streetAddress) {
            searchURL = searchURL + "&streetAddress=" + $scope.search.streetAddress;
        }
        if ($scope.search.zipcode) {
            searchURL = searchURL + "&zipcode=" + $scope.search.zipcode;
        }

        if ($scope.search.contactValueText) {
            searchURL = searchURL + "&contactValueText=" + $scope.search.contactValueText;
        }

        if ($scope.search.attributeValueText) {
            searchURL = searchURL + "&attributeValueText=" + $scope.search.attributeValueText;
        }
        if ($scope.search.attributeValueNumber) {
            searchURL = searchURL + "&attributeValueNumber=" + $scope.search.attributeValueNumber;
        }
        if ($scope.search.attributeValueDate) {
            searchURL = searchURL + "&attributeValueDate=" + $scope.search.attributeValueDate;
        }


        $http.get(searchURL)
            .then(function(response) {
                $scope.personList = response.data;
                if ($scope.searchType === "all") {
                    $scope.searchEnterprise();
                }
            });
    };

    $scope.searchEnterprise = function () {
        var searchURL = "http://localhost:8080/subject/search/enterprise?";


        if ($scope.search.lastName) {
            searchURL = searchURL + "&name=" + $scope.search.lastName;
        }

        if ($scope.search.country) {
            searchURL = searchURL + "&country=" + $scope.search.country;
        }
        if ($scope.search.county) {
            searchURL = searchURL + "&county=" + $scope.search.county;
        }
        if ($scope.search.townVillage) {
            searchURL = searchURL + "&townVillage=" + $scope.search.townVillage;
        }
        if ($scope.search.streetAddress) {
            searchURL = searchURL + "&streetAddress=" + $scope.search.streetAddress;
        }
        if ($scope.search.zipcode) {
            searchURL = searchURL + "&zipcode=" + $scope.search.zipcode;
        }

        if ($scope.search.contactValueText) {
            searchURL = searchURL + "&contactValueText=" + $scope.search.contactValueText;
        }

        if ($scope.search.attributeValueText) {
            searchURL = searchURL + "&attributeValueText=" + $scope.search.attributeValueText;
        }
        if ($scope.search.attributeValueNumber) {
            searchURL = searchURL + "&attributeValueNumber=" + $scope.search.attributeValueNumber;
        }
        if ($scope.search.attributeValueDate) {
            searchURL = searchURL + "&attributeValueDate=" + $scope.search.attributeValueDate;
        }


        $http.get(searchURL)
            .then(function(response) {
                $scope.enterpriseList = response.data;
                if ($scope.searchType === "all") {
                    $scope.searchEmployee();
                }
            });
    };

    $scope.searchEmployee = function () {
        var searchURL = "http://localhost:8080/subject/search/employee?";

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

        if ($scope.search.country) {
            searchURL = searchURL + "&country=" + $scope.search.country;
        }
        if ($scope.search.county) {
            searchURL = searchURL + "&county=" + $scope.search.county;
        }
        if ($scope.search.townVillage) {
            searchURL = searchURL + "&townVillage=" + $scope.search.townVillage;
        }
        if ($scope.search.streetAddress) {
            searchURL = searchURL + "&streetAddress=" + $scope.search.streetAddress;
        }
        if ($scope.search.zipcode) {
            searchURL = searchURL + "&zipcode=" + $scope.search.zipcode;
        }

        if ($scope.search.contactValueText) {
            searchURL = searchURL + "&contactValueText=" + $scope.search.contactValueText;
        }

        if ($scope.search.attributeValueText) {
            searchURL = searchURL + "&attributeValueText=" + $scope.search.attributeValueText;
        }
        if ($scope.search.attributeValueNumber) {
            searchURL = searchURL + "&attributeValueNumber=" + $scope.search.attributeValueNumber;
        }
        if ($scope.search.attributeValueDate) {
            searchURL = searchURL + "&attributeValueDate=" + $scope.search.attributeValueDate;
        }


        $http.get(searchURL)
            .then(function(response) {
                $scope.employeeList = response.data;
                if ($scope.searchType === "all") {
                    $scope.searchCustomer();
                }
            });
    };

    $scope.searchCustomer = function () {
        var searchURL = "http://localhost:8080/subject/search/customer?";

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

        if ($scope.search.country) {
            searchURL = searchURL + "&country=" + $scope.search.country;
        }
        if ($scope.search.county) {
            searchURL = searchURL + "&county=" + $scope.search.county;
        }
        if ($scope.search.townVillage) {
            searchURL = searchURL + "&townVillage=" + $scope.search.townVillage;
        }
        if ($scope.search.streetAddress) {
            searchURL = searchURL + "&streetAddress=" + $scope.search.streetAddress;
        }
        if ($scope.search.zipcode) {
            searchURL = searchURL + "&zipcode=" + $scope.search.zipcode;
        }

        if ($scope.search.contactValueText) {
            searchURL = searchURL + "&contactValueText=" + $scope.search.contactValueText;
        }

        if ($scope.search.attributeValueText) {
            searchURL = searchURL + "&attributeValueText=" + $scope.search.attributeValueText;
        }
        if ($scope.search.attributeValueNumber) {
            searchURL = searchURL + "&attributeValueNumber=" + $scope.search.attributeValueNumber;
        }
        if ($scope.search.attributeValueDate) {
            searchURL = searchURL + "&attributeValueDate=" + $scope.search.attributeValueDate;
        }


        $http.get(searchURL)
            .then(function(response) {
                $scope.customerList = response.data;

            });
    };
    
    
    
});