/**
 * transactions.js
 * Handles fetching and rendering the combined transaction history.
 */

function loadTransactions() {
    fetch('/api/transactions')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('transactionTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';

            if (!data || data.length === 0) {
                tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; padding: 20px; color: #64748b;">No transactions recorded.</td></tr>';
                return;
            }

            data.forEach(item => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${item.transactionDate || '-'}</td>
                    <td><strong>${item.type || '-'}</strong></td>
                    <td>${item.category || '-'}</td>
                    <td>${item.description || '-'}</td>
                    <td>&#8377;${Number(item.amount || 0).toFixed(2)}</td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error loading transactions:', error);
            const tbody = document.getElementById('transactionTableBody');
            if (tbody) {
                tbody.innerHTML = '<tr><td colspan="5" style="text-align:center; color: red;">Failed to load transactions.</td></tr>';
            }
        });
}

document.addEventListener('DOMContentLoaded', () => {
    loadTransactions();
});
