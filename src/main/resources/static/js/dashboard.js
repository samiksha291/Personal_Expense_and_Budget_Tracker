/**
 * dashboard.js
 * Handles data fetching and DOM updates for the dashboard.
 */

document.addEventListener('DOMContentLoaded', () => {
    loadDashboardSummary();
    loadSavingsGoalSummary();
});

function loadDashboardSummary() {
    fetch('/api/dashboard/summary')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch dashboard summary');
            }
            return response.json();
        })
        .then(data => {
            const incomeElem = document.getElementById('totalIncome');
            const expensesElem = document.getElementById('totalExpenses');
            const remainingElem = document.getElementById('remaining');

            if (incomeElem) {
                incomeElem.innerHTML = '&#8377;' + Number(data.totalIncome || 0).toFixed(2);
            }
            if (expensesElem) {
                expensesElem.innerHTML = '&#8377;' + Number(data.totalExpenses || 0).toFixed(2);
            }
            if (remainingElem) {
                remainingElem.innerHTML = '&#8377;' + Number(data.remaining || 0).toFixed(2);
            }
        })
        .catch(error => {
            console.error('Error fetching dashboard summary:', error);
        });
}

function loadSavingsGoalSummary() {
    fetch('/api/savings-goals/latest')
        .then(response => {
            if (response.status === 204 || !response.ok) {
                return null;
            }
            return response.json();
        })
        .then(goal => {
            if (goal && goal.goalName) {
                const goalTitle = document.getElementById('goalTitle');
                const progressBar = document.getElementById('progressBar');
                const progressText = document.getElementById('progressText');

                if (goalTitle) {
                    goalTitle.innerText = '🎯 ' + goal.goalName;
                }
                const percent = goal.progress != null ? Number(goal.progress).toFixed(1) : 0;
                if (progressBar) {
                    progressBar.style.width = percent + '%';
                }
                if (progressText) {
                    progressText.innerHTML = 
                        '&#8377;' + Number(goal.currentAmount || 0).toFixed(2) + 
                        ' saved of &#8377;' + Number(goal.targetAmount || 0).toFixed(2) + 
                        ' (' + percent + '% completed)';
                }
            }
        })
        .catch(error => {
            console.error('Error fetching savings goal:', error);
        });
}
