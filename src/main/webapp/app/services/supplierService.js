angular.module('app').factory('SupplierService', ['$http', function($http) {
    const BASE_URL = 'http://localhost:8080/neostore/api/v1/fornecedores';

    function listSuppliers() {
        return $http.get(BASE_URL)
            .then(response => response.data)
            .catch(error => {
                console.error("Erro ao listar fornecedores", error);
                throw error;
            });
    }

    function listSuppliersPaginated(page = 1, size = 5) {
        return $http.get(`${BASE_URL}/paginado?page=${page}&size=${size}`)
            .then(response => response.data)
            .catch(error => {
                console.error("Erro ao listar fornecedores paginados", error);
                throw error;
            });
    }

    function createSupplier(supplier) {
        return $http.post(BASE_URL, supplier)
            .then(response => response.data)
            .catch(error => {
                console.error("Erro ao criar fornecedor", error);
                throw error;
            });
    }

    function updateSupplier(id, supplier) {
        let updatedSupplier = angular.copy(supplier);
        delete updatedSupplier.id;

        return $http.put(`${BASE_URL}/${id}`, updatedSupplier)
            .then(response => response.data)
            .catch(error => {
                console.error("Erro ao atualizar fornecedor", error);
                throw error;
            });
    }

    function deleteSupplier(id) {
        return $http.delete(`${BASE_URL}/${id}`)
            .then(response => response.data)
            .catch(error => {
                console.error("Erro ao remover fornecedor", error);
                throw error;
            });
    }

    return {
        listSuppliers: listSuppliers,
        listSuppliersPaginated: listSuppliersPaginated,
        createSupplier: createSupplier,
        updateSupplier: updateSupplier,
        deleteSupplier: deleteSupplier
    };
}]);