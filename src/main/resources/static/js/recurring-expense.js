/**
 * recurring-expense.js
 * Handles operations related to recurring expenses: adding and viewing.
 */

// ----------------------------------------------------
// 1. Add Recurring Expense Logic (for recurring-expense.html)
// ----------------------------------------------------
function initAddRecurringExpense() {
    const nextDueDateInput = document.getElementById('nextDueDate');
    if (nextDueDateInput && !nextDueDateInput.value) {
        nextDueDateInput.value = new Date().toISOString().split('T')[0];
    }

    const recurringForm = document.getElementById('recurringForm');
    if (recurringForm) {
        recurringForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const expenseName = document.getElementById('expenseName').value;
            const amount = parseFloat(document.getElementById('amount').value);
            const categoryId = parseInt(document.getElementById('categoryId').value, 10);
            const frequency = document.getElementById('frequency').value;
            const nextDueDate = document.getElementById('nextDueDate').value;
            const description = document.getElementById('description').value;

            const payload = {
                expenseName: expenseName,
                amount: amount,
                categoryId: categoryId,
                frequency: frequency,
                nextDueDate: nextDueDate,
                description: description
            };

            const submitBtn = document.getElementById('submitBtn');
            if (submitBtn) submitBtn.disabled = true;

            fetch('/api/recurring-expenses', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'recurring-expense-view.html';
                } else {
                    alert('Failed to save recurring expense. Please try again.');
                    if (submitBtn) submitBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error saving recurring expense:', error);
                alert('An error occurred while saving recurring expense.');
                if (submitBtn) submitBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// 2. View Recurring Expenses Logic (for recurring-expense-view.html)
// ----------------------------------------------------
function initRecurringExpenseView() {
    fetch('/api/recurring-expenses')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('recurringTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';

            if (!data || data.length === 0) {
                tbody.innerHTML = '<tr><td colspan="6" class="no-data">No recurring expenses found.</td></tr>';
                return;
            }

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.expenseName || '-'}</td>
                    <td>&#8377;${Number(item.amount || 0).toFixed(2)}</td>
                    <td>${item.categoryName || (item.category ? item.category.categoryName : '-')}</td>
                    <td>${item.frequency || '-'}</td>
                    <td>${item.nextDueDate || '-'}</td>
                    <td>${item.description || '-'}</td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error loading recurring expenses:', error);
            const tbody = document.getElementById('recurringTableBody');
            if (tbody) {
                tbody.innerHTML = '<tr><td colspan="6" class="no-data" style="color:red;">Failed to load recurring expenses.</td></tr>';
            }
        });
}

// ----------------------------------------------------
// Auto-detect page on DOMContentLoaded
// ----------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('recurringForm')) {
        initAddRecurringExpense();
    } else if (document.getElementById('recurringTableBody')) {
        initRecurringExpenseView();
    }
});
