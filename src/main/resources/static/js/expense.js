/**
 * expense.js
 * Handles operations related to expenses: adding, editing, listing, filtering, and deleting.
 */

// ----------------------------------------------------
// 1. Add Expense Logic (for add-expense.html)
// ----------------------------------------------------
function initAddExpense() {
    const expenseDateInput = document.getElementById('expenseDate');
    if (expenseDateInput && !expenseDateInput.value) {
        expenseDateInput.value = new Date().toISOString().split('T')[0];
    }

    const expenseForm = document.getElementById('expenseForm');
    if (expenseForm) {
        expenseForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const categoryId = parseInt(document.getElementById('categoryId').value, 10);
            const amount = parseFloat(document.getElementById('amount').value);
            const expenseDate = document.getElementById('expenseDate').value;
            const description = document.getElementById('description').value;

            const payload = {
                categoryId: categoryId,
                amount: amount,
                expenseDate: expenseDate,
                description: description
            };

            const submitBtn = document.getElementById('submitBtn');
            if (submitBtn) submitBtn.disabled = true;

            fetch('/api/expenses', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'expense-success.html';
                } else {
                    alert('Failed to add expense. Please try again.');
                    if (submitBtn) submitBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error adding expense:', error);
                alert('An error occurred while adding expense.');
                if (submitBtn) submitBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// 2. Edit Expense Logic (for edit-expense.html)
// ----------------------------------------------------
function initEditExpense() {
    const urlParams = new URLSearchParams(window.location.search);
    const expenseId = urlParams.get('expenseId');

    if (!expenseId) {
        alert('No expense specified to edit.');
        window.location.href = 'expense-history.html';
        return;
    }

    const idInput = document.getElementById('expenseId');
    if (idInput) idInput.value = expenseId;

    // Load existing expense
    fetch(`/api/expenses/${expenseId}`)
        .then(response => {
            if (!response.ok) throw new Error('Expense not found');
            return response.json();
        })
        .then(exp => {
            document.getElementById('categoryId').value = exp.categoryId;
            document.getElementById('amount').value = exp.amount;
            document.getElementById('expenseDate').value = exp.expenseDate;
            document.getElementById('description').value = exp.description || '';
        })
        .catch(error => {
            console.error('Error fetching expense:', error);
            alert('Expense not found.');
            window.location.href = 'expense-history.html';
        });

    const editForm = document.getElementById('editExpenseForm');
    if (editForm) {
        editForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const categoryId = parseInt(document.getElementById('categoryId').value, 10);
            const amount = parseFloat(document.getElementById('amount').value);
            const expenseDate = document.getElementById('expenseDate').value;
            const description = document.getElementById('description').value;

            const payload = {
                categoryId: categoryId,
                amount: amount,
                expenseDate: expenseDate,
                description: description
            };

            const updateBtn = document.getElementById('updateBtn');
            if (updateBtn) updateBtn.disabled = true;

            fetch(`/api/expenses/${expenseId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'expense-history.html';
                } else {
                    alert('Failed to update expense. Please try again.');
                    if (updateBtn) updateBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error updating expense:', error);
                alert('An error occurred while updating expense.');
                if (updateBtn) updateBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// 3. Expense History Logic (for expense-history.html)
// ----------------------------------------------------
function loadExpenses(params = {}) {
    const query = new URLSearchParams();
    if (params.categoryId) query.append('categoryId', params.categoryId);
    if (params.expenseDate) query.append('expenseDate', params.expenseDate);
    if (params.amount) query.append('amount', params.amount);

    const url = '/api/expenses' + (query.toString() ? '?' + query.toString() : '');

    fetch(url)
        .then(response => response.json())
        .then(expenses => {
            const tbody = document.getElementById('expenseTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';

            if (!expenses || expenses.length === 0) {
                tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; padding: 20px; color: #64748b;">No expenses found.</td></tr>';
                return;
            }

            expenses.forEach(exp => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${exp.expenseDate || '-'}</td>
                    <td>${exp.categoryId || '-'}</td>
                    <td>${exp.description || '-'}</td>
                    <td>&#8377;${Number(exp.amount).toFixed(2)}</td>
                    <td>
                        <a class="edit-link" href="edit-expense.html?expenseId=${exp.expenseId}">
                            Edit
                        </a>
                        <button type="button" class="delete-button" onclick="deleteExpense(${exp.expenseId})">
                            Delete
                        </button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error loading expenses:', error);
            const tbody = document.getElementById('expenseTableBody');
            if (tbody) {
                tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; color: red;">Failed to load expenses.</td></tr>';
            }
        });
}

function deleteExpense(expenseId) {
    if (!confirm('Are you sure you want to delete this expense?')) return;

    fetch(`/api/expenses/${expenseId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            triggerExpenseFilter();
        } else {
            alert('Failed to delete expense.');
        }
    })
    .catch(error => {
        console.error('Error deleting expense:', error);
        alert('An error occurred while deleting expense.');
    });
}

function triggerExpenseFilter() {
    const categoryId = document.getElementById('categoryId') ? document.getElementById('categoryId').value : '';
    const expenseDate = document.getElementById('expenseDate') ? document.getElementById('expenseDate').value : '';
    const amount = document.getElementById('amount') ? document.getElementById('amount').value : '';

    loadExpenses({ categoryId, expenseDate, amount });
}

function initExpenseHistory() {
    const filterForm = document.getElementById('filterForm');
    if (filterForm) {
        filterForm.addEventListener('submit', function (e) {
            e.preventDefault();
            triggerExpenseFilter();
        });
    }

    const clearBtn = document.getElementById('clearBtn');
    if (clearBtn) {
        clearBtn.addEventListener('click', function (e) {
            e.preventDefault();
            if (document.getElementById('categoryId')) document.getElementById('categoryId').value = '';
            if (document.getElementById('expenseDate')) document.getElementById('expenseDate').value = '';
            if (document.getElementById('amount')) document.getElementById('amount').value = '';
            loadExpenses();
        });
    }

    loadExpenses();
}

// ----------------------------------------------------
// Page auto-detection on DOMContentLoaded
// ----------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('expenseForm')) {
        initAddExpense();
    } else if (document.getElementById('editExpenseForm')) {
        initEditExpense();
    } else if (document.getElementById('expenseTableBody')) {
        initExpenseHistory();
    }
});
