angular.module('app').controller('SupplierController', function(SupplierService) {
    const ctrl = this;

    ctrl.suppliers = [];
    ctrl.supplier = {};
    ctrl.pagination = {
        conteudo: [],
        paginaAtual: 1,
        tamanhoPagina: 5,
        totalRegistros: 0,
        totalPaginas: 0
    };

    function validarCnpj(cnpj) {
        const regex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/;
        return regex.test(cnpj);
    }

    ctrl.loadSuppliers = function() {
        SupplierService.listSuppliersPaginated(ctrl.pagination.paginaAtual, ctrl.pagination.tamanhoPagina)
            .then(function(data) {
                ctrl.pagination = data;
            })
            .catch(function(error) {
                const message = error.data?.userMessage || error.data?.detail || "Erro ao carregar fornecedores";
                alert(message);
                console.error("Erro ao carregar fornecedores:", error);
            });
    };

    ctrl.saveSupplier = function() {
        if (!validarCnpj(ctrl.supplier.cnpj)) {
            alert("CNPJ inválido. O formato correto é XX.XXX.XXX/XXXX-XX");
            return;
        }

        if (ctrl.supplier.id) {
            SupplierService.updateSupplier(ctrl.supplier.id, ctrl.supplier)
                .then(function() {
                    ctrl.loadSuppliers();
                    ctrl.supplier = {};
                })
                .catch(function(error) {
                    const message = error.data?.userMessage || error.data?.detail || "Erro ao atualizar fornecedor";
                    alert(message);
                    console.error("Erro ao atualizar fornecedor:", error);
                });
        } else {
            SupplierService.createSupplier(ctrl.supplier)
                .then(function() {
                    ctrl.loadSuppliers();
                    ctrl.supplier = {};
                })
                .catch(function(error) {
                    const message = error.data?.userMessage || error.data?.detail || "Erro ao criar fornecedor";
                    alert(message);
                    console.error("Erro ao criar fornecedor:", error);
                });
        }
    };

    ctrl.editSupplier = function(supplier) {
        ctrl.supplier = angular.copy(supplier);
    };

    ctrl.cancelEdit = function() {
        ctrl.supplier = {};
    };

    ctrl.deleteSupplier = function(supplier) {
        if (confirm("Tem certeza que deseja excluir este fornecedor?")) {
            SupplierService.deleteSupplier(supplier.id)
                .then(function() {
                    ctrl.loadSuppliers();
                })
                .catch(function(error) {
                    const message = error.data?.userMessage || error.data?.detail || "Erro ao excluir fornecedor";
                    alert(message);
                    console.error("Erro ao excluir fornecedor:", error);
                });
        }
    };

    ctrl.goToPage = function(page) {
        if (page < 1 || page > ctrl.pagination.totalPaginas || page === ctrl.pagination.paginaAtual) {
            return;
        }
        ctrl.pagination.paginaAtual = page;
        ctrl.loadSuppliers();
    };

    ctrl.getPageRange = function() {
        const range = [];
        const maxVisiblePages = 5;
        const totalPages = ctrl.pagination.totalPaginas || 1;

        let startPage = Math.max(1, ctrl.pagination.paginaAtual - Math.floor(maxVisiblePages / 2));
        let endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

        if (endPage - startPage + 1 < maxVisiblePages) {
            startPage = Math.max(1, endPage - maxVisiblePages + 1);
        }

        for (let i = startPage; i <= endPage; i++) {
            range.push(i);
        }

        return range;
    };

    ctrl.loadSuppliers();
});