import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ProductService from '../services/ProductService';
import { Table, Button, Container, Alert } from 'react-bootstrap';
import { Edit, Delete } from '@mui/icons-material';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await ProductService.getAllProducts();
      setProducts(response.data);
    } catch (err) {
      setError('Erreur lors du chargement des produits');
      console.error(err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await ProductService.deleteProduct(id);
      fetchProducts();
    } catch (err) {
      setError('Erreur lors de la suppression');
      console.error(err);
    }
  };

  return (
    <Container className="mt-4">
      <h2>Liste des Produits</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      
      <Link to="/add" className="btn btn-primary mb-3">
        Ajouter un produit
      </Link>
      
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.description}</td>
              <td>{product.price}</td>
              <td>{product.quantity}</td>
              <td>
                <Link to={`/edit/${product.id}`} className="btn btn-sm btn-warning me-2">
                  <Edit fontSize="small" /> Modifier
                </Link>
                <Button variant="danger" size="sm" onClick={() => handleDelete(product.id)}>
                  <Delete fontSize="small" /> Supprimer
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default ProductList;