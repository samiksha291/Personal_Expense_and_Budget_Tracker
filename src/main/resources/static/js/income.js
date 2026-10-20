/**
 * income.js
 * Handles operations related to income: adding, listing, filtering, and deleting.
 */

// ----------------------------------------------------
// 1. Add Income Logic (for add-income.html)
// ----------------------------------------------------
function initAddIncome() {
    const incomeDateInput = document.getElementById('incomeDate');
    if (incomeDateInput && !incomeDateInput.value) {
        incomeDateInput.value = new Date().toISOString().split('T')[0];
    }

    const incomeForm = document.getElementById('incomeForm');
    if (incomeForm) {
        incomeForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const source = document.getElementById('source').value;
            const amount = parseFloat(document.getElementById('amount').value);
            const incomeDate = document.getElementById('incomeDate').value;
            const description = document.getElementById('description').value;

            const payload = {
                source: source,
                amount: amount,
                incomeDate: incomeDate,
                description: description
            };

            const submitBtn = document.getElementById('submitBtn');
            if (submitBtn) submitBtn.disabled = true;

            fetch('/api/income', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'income-success.html';
                } else {
                    alert('Failed to add income. Please try again.');
                    if (submitBtn) submitBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error adding income:', error);
                alert('An error occurred while adding income.');
                if (submitBtn) submitBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// 2. Income History Logic (for income-history.html)
// ----------------------------------------------------
function loadIncome(params = {}) {
    const query = new URLSearchParams();
    if (params.source) query.append('source', params.source);
    if (params.incomeDate) query.append('incomeDate', params.incomeDate);
    if (params.amount) query.append('amount', params.amount);

    const url = '/api/income' + (query.toString() ? '?' + query.toString() : '');

    fetch(url)
        .then(response => response.json())
        .then(incomes => {
            const tbody = document.getElementById('incomeTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';

            if (!incomes || incomes.length === 0) {
                tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; padding: 20px; color: #64748b;">No income records found.</td></tr>';
                return;
            }

            incomes.forEach(inc => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${inc.incomeDate || '-'}</td>
                    <td>${inc.source || '-'}</td>
                    <td>${inc.description || '-'}</td>
                    <td>&#8377;${Number(inc.amount).toFixed(2)}</td>
                    <td>
                        <button type="button" class="delete-button" onclick="deleteIncome(${inc.incomeId})">
                            Delete
                        </button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error loading income:', error);
            const tbody = document.getElementById('incomeTableBody');
            if (tbody) {
                tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; color: red;">Failed to load income.</td></tr>';
            }
        });
}

function deleteIncome(incomeId) {
    if (!confirm('Are you sure you want to delete this income record?')) return;

    fetch(`/api/income/${incomeId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            triggerIncomeFilter();
        } else {
            alert('Failed to delete income record.');
        }
    })
    .catch(error => {
        console.error('Error deleting income:', error);
        alert('An error occurred while deleting income.');
    });
}

function triggerIncomeFilter() {
    const source = document.getElementById('source') ? document.getElementById('source').value : '';
    const incomeDate = document.getElementById('incomeDate') ? document.getElementById('incomeDate').value : '';
    const amount = document.getElementById('amount') ? document.getElementById('amount').value : '';

    loadIncome({ source, incomeDate, amount });
}

function initIncomeHistory() {
    const filterForm = document.getElementById('filterForm');
    if (filterForm) {
        filterForm.addEventListener('submit', function (e) {
            e.preventDefault();
            triggerIncomeFilter();
        });
    }

    const clearBtn = document.getElementById('clearBtn');
    if (clearBtn) {
        clearBtn.addEventListener('click', function (e) {
            e.preventDefault();
            if (document.getElementById('source')) document.getElementById('source').value = '';
            if (document.getElementById('incomeDate')) document.getElementById('incomeDate').value = '';
            if (document.getElementById('amount')) document.getElementById('amount').value = '';
            loadIncome();
        });
    }

    loadIncome();
}

// ----------------------------------------------------
// Page auto-detection on DOMContentLoaded
// ----------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('incomeForm')) {
        initAddIncome();
    } else if (document.getElementById('incomeTableBody')) {
        initIncomeHistory();
    }
});
