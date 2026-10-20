/**
 * budget.js
 * Handles setting and viewing monthly budgets.
 */

// ----------------------------------------------------
// 1. Set Budget Logic (for budget.html)
// ----------------------------------------------------
function initSetBudget() {
    const monthInput = document.getElementById('month');
    const yearInput = document.getElementById('year');

    const now = new Date();
    if (monthInput && !monthInput.value) {
        monthInput.value = now.getMonth() + 1;
    }
    if (yearInput && !yearInput.value) {
        yearInput.value = now.getFullYear();
    }

    const budgetForm = document.getElementById('budgetForm');
    if (budgetForm) {
        budgetForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const month = parseInt(document.getElementById('month').value, 10);
            const year = parseInt(document.getElementById('year').value, 10);
            const amount = parseFloat(document.getElementById('amount').value);

            const payload = {
                month: month,
                year: year,
                amount: amount
            };

            const submitBtn = document.getElementById('submitBtn');
            if (submitBtn) submitBtn.disabled = true;

            fetch('/api/budget', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = `budget-view.html?month=${month}&year=${year}`;
                } else {
                    alert('Failed to save budget. Please try again.');
                    if (submitBtn) submitBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error saving budget:', error);
                alert('An error occurred while saving budget.');
                if (submitBtn) submitBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// 2. View Budget Summary Logic (for budget-view.html)
// ----------------------------------------------------
function initBudgetView() {
    const urlParams = new URLSearchParams(window.location.search);
    const now = new Date();
    const month = urlParams.get('month') || (now.getMonth() + 1);
    const year = urlParams.get('year') || now.getFullYear();

    const titleElem = document.getElementById('budgetHeaderTitle');
    if (titleElem) {
        titleElem.innerText = `Budget for ${month}/${year}`;
    }

    fetch(`/api/budget?month=${month}&year=${year}`)
        .then(response => response.json())
        .then(data => {
            const budgetElem = document.getElementById('budgetAmount');
            const spentElem = document.getElementById('spentAmount');
            const remElem = document.getElementById('remainingAmount');

            if (budgetElem) {
                budgetElem.innerHTML = '&#8377;' + Number(data.budget || 0).toFixed(2);
            }
            if (spentElem) {
                spentElem.innerHTML = '&#8377;' + Number(data.totalExpenses || 0).toFixed(2);
            }
            if (remElem) {
                remElem.innerHTML = '&#8377;' + Number(data.remaining || 0).toFixed(2);
                if (data.remaining < 0) {
                    remElem.className = 'budget-amount spent';
                } else {
                    remElem.className = 'budget-amount remaining';
                }
            }
        })
        .catch(error => {
            console.error('Error fetching budget summary:', error);
        });
}

// ----------------------------------------------------
// Auto-detect page on DOMContentLoaded
// ----------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('budgetForm')) {
        initSetBudget();
    } else if (document.getElementById('budgetHeaderTitle')) {
        initBudgetView();
    }
});
