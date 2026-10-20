/**
 * summary.js
 * Handles fetching and rendering the monthly financial summary.
 */

function loadMonthlySummary() {
    fetch('/api/dashboard/monthly-summary')
        .then(response => response.json())
        .then(data => {
            const incomeElem = document.getElementById('summaryIncome');
            const expensesElem = document.getElementById('summaryExpenses');
            const categoryElem = document.getElementById('summaryCategory');
            const remainingElem = document.getElementById('summaryRemaining');

            if (incomeElem) {
                incomeElem.innerHTML = '&#8377;' + Number(data.totalIncome || 0).toFixed(2);
            }
            if (expensesElem) {
                expensesElem.innerHTML = '&#8377;' + Number(data.totalExpenses || 0).toFixed(2);
            }
            if (categoryElem) {
                categoryElem.innerText = data.highestCategory || 'None';
            }
            if (remainingElem) {
                remainingElem.innerHTML = '&#8377;' + Number(data.remaining || 0).toFixed(2);
            }
        })
        .catch(error => {
            console.error('Error fetching monthly summary:', error);
        });
}

document.addEventListener('DOMContentLoaded', () => {
    loadMonthlySummary();
});
