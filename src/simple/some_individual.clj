;; The contents of this file are subject to the LGPL License, Version 3.0.
;;
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;;
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;;
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns simple.some-individual
  (:use [tawny.owl])
  (:require [tawny.reasoner :as r]))

;; ## Introduction
;;
;; This demonstrates the inferences that can be made about individuals as a
;; result of various restrictions.


;; ## Preamble
;;
;; Set the reasoner and define the ontology, as well as a property and a
;; class.

(r/reasoner-factory :hermit)
(defontology ind)

(defclass X)
(defoproperty r)

;; ## Main Classes
;;
;; First we define class `A` as being equivalent to anything that has an `r`
;; relationship to an instance of `X`.
(defclass A :equivalent (owlsome r X))

;; While we define `B` as being equivalent to anything which has an `r`
;; relationship to the individual `iX`, which is an individual of type `X`.
(defindividual iX :type X)
(defclass B :equivalent (hasvalue r iX))

;; And `C` is defined as equivalent to things which may or may not have an `r`
;; relationship to anything, but if they do, it will be to an instance of `X`.
(defclass C :equivalent (only r X))

;; Finally, we define an individual `i` which has an `r` relationship to `iX`
(defindividual i
  :fact (fact r iX))

;; So, what can we conclude? Well, although we have not explicitly stated that
;; `i` is a individual of class `A`, we can infer that it is, because it has
;; an `r` relationship to an individual of `X`, which is the definition of an
;; individual of `A`.
(r/instances A)

;; Likewise, we can conclude that `i` in an individual of class `B`, again
;; because it relates specifically to `iX` which is the definition of `B`.
(r/instances B)

;; We do not, however, conclude that `i` is an individual of `C` however.
;; This is because of open world reasoning; as far as we know, everything that
;; `i` has an `r` relationship to is of type `X`, but we don't know
;; everything. There could be another `r` relationship because we have not
;; said that there cannot be. Hence, we cannot conclude that `i` is an
;; instance of `C`.
(r/instances C)

;; ## Conclusions
;;
;; The some, or existential relationship describes relationships which
;; necessarily exist to individuals of a specific class; the hasvalue
;; relationship says the same but to a specific individual; an individual can
;; be inferred to be a member of a class as a result of either of these
;; statements.

;; ## See also
;;
;; See [only_individual](#simple.only_individual) for inferences that can be
;; made as a result of `only` statements.
